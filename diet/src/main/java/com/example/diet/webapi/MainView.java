package com.example.diet.webapi;

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

@CssImport("./shared-styles.css")
public class MainView extends AppLayout {
    public MainView() {
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
        RouterLink loginLink = new RouterLink("Login", LoginView.class);
        RouterLink mainLink = new RouterLink("MainView(delete later)", MainPage.class);
        RouterLink registerLink = new RouterLink("Registration", RegistrationView.class);
        RouterLink delegationLink = new RouterLink("Delegation", DelegationView.class);
        delegationLink.setHighlightCondition(HighlightConditions.sameLocation());
        loginLink.setHighlightCondition(HighlightConditions.sameLocation());
        userLink.setHighlightCondition(HighlightConditions.sameLocation());
        registerLink.setHighlightCondition(HighlightConditions.sameLocation());
        mainLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(userLink, mainLink,loginLink,registerLink,delegationLink));
    }
}