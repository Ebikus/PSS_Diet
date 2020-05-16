package com.example.diet.webapi;

import com.example.diet.model.Delegarion;
import com.example.diet.model.User;
import com.example.diet.service.DelegationService;
import com.example.diet.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;

@CssImport("./shared-styles.css")
@Route(value="adminDelegation", layout = MainView.class)
@PageTitle("Delegation | Super Apka")
public class AdminDelegationView extends VerticalLayout {
    @Autowired
    DelegationService delegationService;
    @Autowired
    UserService userService;

    private User user;
    private final DelegationForm form;
    private Grid<Delegarion> grid = new Grid<>(Delegarion.class);

    public AdminDelegationView(DelegationService delegationService, UserService userService){
        this.delegationService = delegationService;
        this.userService = userService;
        user = userService.finndUserByEmail(currentUser());
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new DelegationForm();
        form.addListener(DelegationForm.SaveEvent.class, this::saveDelegation);
        form.addListener(DelegationForm.DeleteEvent.class, this::deleteDelegation);
        form.addListener(DelegationForm.ModifyEvent.class, this::modifyDelegation);
        form.addListener(DelegationForm.CloseEvent.class, e -> closeEditor());

        grid.setSizeFull();
        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();


        add(getToolBar(),content);
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        Button addDelegationButton = new Button("Add Delegation", click -> addDelegation());
        StreamResource res = new StreamResource("file.pdf", () -> new ByteArrayInputStream("sebek".getBytes()));

        Anchor generatePDF = new Anchor(res,"");
        generatePDF.getElement().setAttribute("download", true);
        generatePDF.add(new Button("Download PDF" ,new Icon(VaadinIcon.DOWNLOAD_ALT)));
        HorizontalLayout toolbar = new HorizontalLayout(addDelegationButton, generatePDF);
        return toolbar;
    }

    /*private InputStream getInputStream(){
        try{
            StringWriter stringWriter = new StringWriter();

            CsvW

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }*/

    private void configureGrid(){
        grid.addClassName("grid");
        grid.setSizeFull();
        grid.setColumns("description","dateTimeStrat","dataTimeStop","travelDietAmount","breakfastNumber","dinnerNumber","supperNumber","transportType",
                "ticketPrice","autoCapacity","km","accomodationPrice","otherTicketsPrice","otherOutlayDesc","otherOutlayPrice");
        grid.getColumns().forEach(delegationColumn -> delegationColumn.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(evt -> editDelegation(evt.getValue()));

    }

    private void addDelegation() {
        grid.asSingleSelect().clear();
        editDelegation(new Delegarion());
    }

    private void deleteDelegation(DelegationForm.DeleteEvent del) {
        Date date = java.sql.Date.valueOf(LocalDate.now());
        if (del.getDelegation().getDateTimeStrat().compareTo(date) > 0){
            delegationService.removeDelegation(user.getIduser(), del.getDelegation().getIddelegation());
            updateList();
            closeEditor();
        }

    }

    private void saveDelegation(DelegationForm.SaveEvent del) {
        delegationService.addDelegation(user.getIduser(),del.getDelegation());
        updateList();
        closeEditor();
    }

    private void modifyDelegation(DelegationForm.ModifyEvent del){
        Date date = java.sql.Date.valueOf(LocalDate.now());
        if (del.getDelegation().getDateTimeStrat().compareTo(date) > 0){
            delegationService.changeDelegation(del.getDelegation().getIddelegation(),del.getDelegation());
            updateList();
            closeEditor();
        }
    }

    private void editDelegation(Delegarion delegation) {
        if(delegation == null){
            closeEditor();
        }else{
            form.setDelegation(delegation);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setDelegation(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList(){
        grid.setItems(delegationService.getAllDelegation());
    }

    public String currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        return currentName;
    }


}
