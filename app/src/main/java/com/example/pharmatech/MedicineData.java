package com.example.pharmatech;

public class MedicineData {

    String name,detail,id,price,expdate,shelfno,quantity,TotalPrice;

    public MedicineData() {
    }

    public MedicineData(String name, String detail, String id, String price, String expdate, String shelfno, String quantity, String totalPrice) {
        this.name = name;
        this.detail = detail;
        this.id = id;
        this.price = price;
        this.expdate = expdate;
        this.shelfno = shelfno;
        this.quantity = quantity;
        TotalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getShelfno() {
        return shelfno;
    }

    public void setShelfno(String shelfno) {
        this.shelfno = shelfno;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }
}
