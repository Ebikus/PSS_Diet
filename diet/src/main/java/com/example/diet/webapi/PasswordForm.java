package com.example.diet.webapi;

import com.example.diet.model.User;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.shared.Registration;


public class PasswordForm extends FormLayout {
    private PasswordField password = new PasswordField("New Password");

    private Button modify = new Button("Modify");
    private Button close = new Button("Close");
    BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);

    public PasswordForm(){
        binder.forField(password).asRequired("New password is required").bind("password");
        binder.setBean(new User());

        addClassName("password-Form");
        add(password, createButtonLayout());
        modify.setEnabled(false);
        binder.addStatusChangeListener(statusChangeEvent -> {
            modify.setEnabled(!statusChangeEvent.hasValidationErrors() && !binder.getFields().anyMatch(HasValue::isEmpty));
        });
    }

    public void setUser(User user){
        binder.setBean(user);
    }

    private Component createButtonLayout(){
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        modify.addClickListener(buttonClickEvent -> fireEvent(new ModifyEvent(this, binder.getBean())));
        close.addClickListener(buttonClickEvent -> fireEvent(new PasswordForm.CloseEvent(this)));
        return new HorizontalLayout(modify, close);
    }

    public static abstract class PasswordFormEvent extends ComponentEvent<PasswordForm> {
        private User user;

        protected PasswordFormEvent(PasswordForm source, User user){
            super(source, false);
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }

    public static class ModifyEvent extends PasswordFormEvent{
        ModifyEvent(PasswordForm source, User user){
            super(source, user);
        }
    }

    public static class CloseEvent extends PasswordFormEvent{
        CloseEvent(PasswordForm source){
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener){
        return getEventBus().addListener(eventType, listener);
    }
}
