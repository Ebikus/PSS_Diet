package com.example.diet.webapi;

import com.example.diet.Other.AutoCapacity;
import com.example.diet.Other.TransportType;
import com.example.diet.model.Delegarion;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.converter.LocalDateToDateConverter;
import com.vaadin.flow.data.converter.StringToFloatConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.shared.Registration;

import java.time.ZoneId;

public class DelegationForm extends FormLayout {
    private TextField description = new TextField("Description");
    private TextField travelDietAmount = new TextField("Travel Diet");
    private TextField breakfastNumber = new TextField("Breakfast Number");
    private TextField dinnerNumber = new TextField("Dinner Number");
    private TextField supperNumber = new TextField("Breakfast Number");
    private TextField ticketPrice = new TextField("Ticket Price");
    //private TextField autoCapacity = new TextField("Auto Capacity");
    private TextField km = new TextField("Km");
    private TextField accomodationPrice = new TextField("Accommodation Price");
    private TextField otherTicketsPrice = new TextField("Other Tickets Price");
    private TextField otherOutlayDesc = new TextField("Other Outlay Desc");
    private TextField otherOutlayPrice = new TextField("Other Outlay Price");
    private DatePicker dateTimeStrat = new DatePicker("Date Start");
    private DatePicker dataTimeStop= new DatePicker("Date End");
    private ComboBox<TransportType> transportType = new ComboBox<>("Transport type");
    private ComboBox<AutoCapacity> autoCapacity = new ComboBox<>("Auto Capacity");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button modify = new Button("Modify");
    private Button close = new Button("Close");

    BeanValidationBinder<Delegarion> binder = new BeanValidationBinder<>(Delegarion.class);

    public DelegationForm(){
        binder.forField(description).bind(Delegarion::getDescription, Delegarion::setDescription);
        binder.forField(travelDietAmount).withConverter(new StringToIntegerConverter("Enter a number")).bind(Delegarion::getTravelDietAmount, Delegarion::setTravelDietAmount);
        binder.forField(breakfastNumber).withConverter(new StringToIntegerConverter("Enter a number")).bind(Delegarion::getBreakfastNumber, Delegarion::setBreakfastNumber);
        binder.forField(dinnerNumber).withConverter(new StringToIntegerConverter("Enter a number")).bind(Delegarion::getDinnerNumber, Delegarion::setDinnerNumber);
        binder.forField(supperNumber).withConverter(new StringToIntegerConverter("Enter a number")).bind(Delegarion::getSupperNumber, Delegarion::setSupperNumber);
        binder.forField(ticketPrice).withNullRepresentation("").withConverter(new StringToFloatConverter("Enter a number")).bind(Delegarion::getTicketPrice, Delegarion::setTicketPrice);
        //binder.forField(autoCapacity).withNullRepresentation("").withConverter(new StringToIntegerConverter("Enter a number")).bind(Delegarion::getAutoCapacity, Delegarion::setAutoCapacity);
        binder.forField(km).withNullRepresentation("").withConverter(new StringToFloatConverter("Enter a number")).bind(Delegarion::getKm, Delegarion::setKm);
        binder.forField(accomodationPrice).withNullRepresentation("").withConverter(new StringToFloatConverter("Enter a number")).bind(Delegarion::getAccomodationPrice, Delegarion::setAccomodationPrice);
        binder.forField(otherTicketsPrice).withNullRepresentation("").withConverter(new StringToFloatConverter("Enter a number")).bind(Delegarion::getOtherTicketsPrice, Delegarion::setOtherTicketsPrice);
        binder.forField(otherOutlayDesc).bind(Delegarion::getOtherOutlayDesc, Delegarion::setOtherOutlayDesc);
        binder.forField(otherOutlayPrice).withNullRepresentation("").withConverter(new StringToFloatConverter("Enter a number")).bind(Delegarion::getOtherOutlayPrice, Delegarion::setOtherOutlayPrice);
        binder.forField(dateTimeStrat).withConverter(new LocalDateToDateConverter(ZoneId.systemDefault())).bind("dateTimeStrat");
        binder.forField(dataTimeStop).withConverter(new LocalDateToDateConverter(ZoneId.systemDefault())).bind("dataTimeStop");
        binder.forField(transportType).bind(Delegarion::getTransportType, Delegarion::setTransportType);
        binder.forField(autoCapacity).bind(Delegarion::getAutoCapacity, Delegarion::setAutoCapacity);
        binder.setBean(new Delegarion());

        transportType.setItems(TransportType.values());
        autoCapacity.setItems(AutoCapacity.values());

        addClassName("delegation-form");

        add(
                dateTimeStrat,
                dataTimeStop,
                description,
                travelDietAmount,
                breakfastNumber,
                dinnerNumber,
                supperNumber,
                transportType,
                ticketPrice,
                autoCapacity,
                km,
                accomodationPrice,
                otherTicketsPrice,
                otherOutlayDesc,
                otherOutlayPrice,
                createButtonLayout()
        );
    }

    public void setDelegation(Delegarion delegation){
        binder.setBean(delegation);
    }

    private Component createButtonLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(buttonClickEvent -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(buttonClickEvent -> fireEvent(new CloseEvent(this)));
        modify.addClickListener(buttonClickEvent -> fireEvent(new ModifyEvent(this, binder.getBean())));

        save.setEnabled(false);
        binder.addStatusChangeListener(status -> {
                    save.setEnabled(!status.hasValidationErrors() && !dateTimeStrat.isEmpty() && !dataTimeStop.isEmpty());
                }
        );
        return new HorizontalLayout(save, modify, delete, close);
    }

    private void validateAndSave() {
        if(binder.isValid()){
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    public static abstract class DelegationFormEvent extends ComponentEvent<DelegationForm> {
        private Delegarion delegation;

        protected DelegationFormEvent(DelegationForm source, Delegarion delegation) {
            super(source, false);
            this.delegation = delegation;
        }

        public Delegarion getDelegation() {
            return delegation;
        }
    }

    public static class SaveEvent extends DelegationFormEvent {
        SaveEvent(DelegationForm source, Delegarion delegation) {
            super(source, delegation);
        }
    }

    public static class ModifyEvent extends DelegationFormEvent {
        ModifyEvent(DelegationForm source, Delegarion delegation) {
            super(source, delegation);
        }
    }

    public static class DeleteEvent extends DelegationFormEvent {
        DeleteEvent(DelegationForm source, Delegarion delegation) {
            super(source, delegation);
        }
    }

    public static class CloseEvent extends DelegationFormEvent {
        CloseEvent(DelegationForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
