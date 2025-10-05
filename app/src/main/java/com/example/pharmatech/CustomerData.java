package com.example.pharmatech;

public class CustomerData {

    String customer,doctor,store,details;

    public CustomerData(String customer, String doctor, String store, String details) {
        this.customer = customer;
        this.doctor = doctor;
        this.store = store;
        this.details = details;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
