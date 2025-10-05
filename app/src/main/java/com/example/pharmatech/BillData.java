package com.example.pharmatech;

public class BillData {

    public BillData() {
    }

    String billId,storeName,customer,currentDate,currentTime,medicineDetail;
    double totalAmount;


    public BillData(String billId, String storeName, String customer, String currentDate, String currentTime, String medicineDetail, double totalAmount) {
        this.billId = billId;
        this.storeName = storeName;
        this.customer = customer;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.medicineDetail = medicineDetail;
        this.totalAmount = totalAmount;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getMedicineDetail() {
        return medicineDetail;
    }

    public void setMedicineDetail(String medicineDetail) {
        this.medicineDetail = medicineDetail;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
