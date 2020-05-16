package com.example.diet.webapi;

import com.example.diet.model.User;
import com.example.diet.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("register")
@PageTitle("Register | Super Apka")
public class RegistrationView extends VerticalLayout {

    @Autowired
    private UserService userService;

    FormLayout formLayout = new FormLayout();
    IntegerField iduser = new IntegerField("Id User");
    TextField name = new TextField("Name");
    TextField lastName = new TextField("Last Name");
    TextField email = new TextField("E-Mail");
    PasswordField password = new PasswordField("Password");
    TextField companyName = new TextField("Company Name");
    TextField companyAddress = new TextField("Company Address");
    TextField companyNip = new TextField("Company Nip");

    Button registerButton = new Button("Register");


    public RegistrationView(UserService userService){
        this.userService = userService;
        addClassName("register-view");
        setSizeFull();
        formLayout.add(iduser, name, lastName, email, password, companyName, companyAddress, companyNip, createButtonLayout());
        add(new H1("Register | Super Apka"), formLayout);
    }

    private Component createButtonLayout(){
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        registerButton.addClickListener(buttonClickEvent -> saveUser());

        return new HorizontalLayout(registerButton);
    }
    private void saveUser(){
        User user = new User();
        user.setIduser(iduser.getValue());
        user.setName(name.getValue());
        user.setLastName(lastName.getValue());
        user.setEmail(email.getValue());
        user.setPassword(password.getValue());
        user.setCompanyName(companyName.getValue());
        user.setCompanyAddress(companyAddress.getValue());
        user.setCompanyNip(companyNip.getValue());
        userService.registerUser(user);
    }
}
