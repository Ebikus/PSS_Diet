package com.example.diet.webapi;

import com.example.diet.model.User;
import com.example.diet.service.UserService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.swing.text.html.ListView;

@CssImport("./shared-styles.css")
public class MainView extends AppLayout {

    @Autowired
    UserService userService;
    private User user;

    public MainView(UserService userService) {
        this.userService = userService;
        user = userService.finndUserByEmail(currentUser());
        createHeader();
        createDrawer();
    }
    private void createHeader() {
        H1 logo = new H1("DIET APP");
        logo.addClassName("logo");

        Anchor logout =  new Anchor("/logout", "Log Out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.expand(logo);
        header.addClassName("header");
        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink userLink = new RouterLink("Users", UsersView.class);
        //RouterLink loginLink = new RouterLink("Login", LoginView.class);
        RouterLink mainLink = new RouterLink("Main", MainPage.class);
        RouterLink delegationLink = new RouterLink("Delegations", DelegationView.class);
        RouterLink adminDelegationLink = new RouterLink("All Delegations", AdminDelegationView.class);
        //loginLink.setHighlightCondition(HighlightConditions.sameLocation());
        userLink.setHighlightCondition(HighlightConditions.sameLocation());
        mainLink.setHighlightCondition(HighlightConditions.sameLocation());
        delegationLink.setHighlightCondition(HighlightConditions.sameLocation());
        if(user.getRoles().stream().anyMatch(role -> role.getRoleName().toString().equals("ADMIN"))){
            addToDrawer(new VerticalLayout(mainLink, userLink, delegationLink, adminDelegationLink));
        }else {
            addToDrawer(new VerticalLayout(mainLink, delegationLink));
        }
        //addToDrawer(new VerticalLayout(userLink, mainLink, delegationLink));
    }

    public String currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        return currentName;
    }
}