package com.example.myapplication;

public class PaymentsHelper {
    String SAccNumber,RAccNumber, price;

    public PaymentsHelper (String SAccNumber,String RAccNumber,String price){
        this.SAccNumber = SAccNumber;
        this.RAccNumber = RAccNumber;
        this.price = price;

    }
    public String getSAccNumber() {
        return SAccNumber;
    }

    public void setSAccNumber(String SAccNumber) {
        this.SAccNumber = SAccNumber;
    }

    public String getRAccNumber() {
        return RAccNumber;
    }

    public void setRAccNumber(String RAccNumber) {
        this.RAccNumber = RAccNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
