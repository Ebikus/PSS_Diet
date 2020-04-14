package com.example.diet.webapi;

import com.example.diet.model.User;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.shared.Registration;




public class UserForm extends FormLayout {
    private IntegerField iduser = new IntegerField("ID");
    private PasswordField password = new PasswordField("New Password");
    private TextField name = new TextField("Name");
    private TextField lastName = new TextField("Last Name");
    private TextField email = new TextField("E-Mail");
    private TextField companyName = new TextField("Company Name");
    private TextField companyAddress = new TextField("Company Address");
    private TextField companyNip = new TextField("Company Nip");

    private Button modify = new Button("Modify");
    private Button close = new Button("Close");

    BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);

    public UserForm(){
        binder.forField(iduser).bind("iduser");
        binder.forField(password).bind("password");
        binder.forField(name).bind("name");
        binder.forField(lastName).bind("lastName");
        binder.forField(email).bind("email");
        binder.forField(companyName).bind("companyName");
        binder.forField(companyAddress).bind("companyAddress");
        binder.forField(companyNip).bind("companyNip");
        binder.setBean(new User());

        addClassName("user-form");
        add(iduser,
                password,
                name,
                lastName,
                email,
                companyName,
                companyAddress,
                companyNip,
                createButtonLayout()
        );
        modify.setEnabled(false);
        binder.addStatusChangeListener(status -> {
                    modify.setEnabled(!status.hasValidationErrors() && !binder.getFields().anyMatch(HasValue::isEmpty));
                }
        );
    }

    public void setUser(User user){
        binder.setBean(user);
    }

    private Component createButtonLayout(){
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickShortcut(Key.ESCAPE);

        modify.addClickListener(buttonClickEvent -> fireEvent(new ModifyEvent(this, binder.getBean())));
        close.addClickListener(buttonClickEvent -> fireEvent(new UserForm.CloseEvent(this)));
        return new HorizontalLayout(modify, close);
    }

    public static abstract class UserFormEvent extends ComponentEvent<UserForm> {
        private User user;

        protected UserFormEvent(UserForm source, User user) {
            super(source, false);
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }

    public static class ModifyEvent extends UserFormEvent {
        ModifyEvent(UserForm source, User user) {
            super(source, user);
        }
    }

    public static class CloseEvent extends UserFormEvent {
        CloseEvent(UserForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
