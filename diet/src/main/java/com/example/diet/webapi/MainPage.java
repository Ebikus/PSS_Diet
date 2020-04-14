package com.example.diet.webapi;

import com.example.diet.model.User;
import com.example.diet.service.UserService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@CssImport("./shared-styles.css")
@Route(value="mainpage", layout = MainView.class)
@PageTitle("Main Page | DIET APP")
public class MainPage extends VerticalLayout {

    @Autowired
    private UserService userService;

    User user;

    Label name = new Label();
    Label idUser = new Label();

    public MainPage(UserService userService) {
        this.userService = userService;

        user = userService.finndUserByEmail(currentUser());
        name.setText("Imię: " + user.getName());
        idUser.setText("Id: " + user.getIduser());

        add(name, idUser);
    }

    public String currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        return currentName;
    }



}
