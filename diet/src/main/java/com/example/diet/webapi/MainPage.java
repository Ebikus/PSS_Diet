package com.example.diet.webapi;

import com.example.diet.model.User;
import com.example.diet.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;



@CssImport("./shared-styles.css")
@Route(value="", layout = MainView.class)
@PageTitle("Main Page | DIET APP")
public class MainPage extends VerticalLayout {

    @Autowired
    private UserService userService;

    private User user;
    private final PasswordForm form;

    Label name = new Label();
    Label idUser = new Label();
    Label userLastName = new Label();
    Label userEmail = new Label();

    Button changePassword = new Button("Change password");

    public MainPage(UserService userService) {
        this.userService = userService;
        addClassName("userDataView");
        setSizeFull();

        user = userService.finndUserByEmail(currentUser());
        initialazeFields();

        form = new PasswordForm();
        form.setVisible(false);
        form.addListener(PasswordForm.ModifyEvent.class, this::modifyPassword);
        form.addListener(PasswordForm.CloseEvent.class, e -> closeEditor());
        changePassword.addClickListener(actionEvent -> form.setVisible(true));
        Div content = new Div(new VerticalLayout(idUser,name,userLastName,userEmail, changePassword), form);
        add(content);
    }

    private void initialazeFields(){
        name.setText("Imię: " + user.getName());
        idUser.setText("Id: " + user.getIduser());
        userLastName.setText("Nazwisko: " + user.getLastName());
        userEmail.setText("Email: " + user.getEmail());
    }

    private void modifyPassword(PasswordForm.ModifyEvent usr){
        userService.changePassword(user.getIduser(), usr.getUser().getPassword());
    }

    public void closeEditor(){
        form.setUser(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    public String currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        return currentName;
    }
}
