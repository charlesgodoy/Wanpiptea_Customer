package com.burning_glow.wanpipteacustomer.model;

public class Contact {

    private String Address;
    private String Branch;
    private String Mobile;
    private String Name;

    public Contact() {
    }

    public Contact(String address, String branch, String mobile, String name) {
        Address = address;
        Branch = branch;
        Mobile = mobile;
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public String getBranch() {
        return Branch;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getName() {
        return Name;
    }
}
