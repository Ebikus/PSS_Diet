package com.example.diet.webapi;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

import javax.swing.text.html.ListView;

@CssImport("./shared-styles.css")
public class MainView extends AppLayout {
    public MainView() {
        createHeader();
        createDrawer();
    }
    private void createHeader() {
        H1 logo = new H1("DIET APP");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");
        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink userLink = new RouterLink("Users", UsersView.class);
        RouterLink loginLink = new RouterLink("Login", LoginView.class);
        loginLink.setHighlightCondition(HighlightConditions.sameLocation());
        userLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(userLink, loginLink));
    }
}


