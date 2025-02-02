package com.example.orderfoods.Model;

import java.util.List;

public class Request {
    private String name,phone,address,total;
    List<Order> foods; //list food order

    public Request() {
    }

    public Request(String name, String phone, String address, String total, List<Order> foods) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.total = total;
        this.foods = foods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
