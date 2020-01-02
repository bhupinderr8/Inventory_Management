package com.example.inventory.dataObject;

import java.util.Date;

public class userObject {
    private String name;
    private String employeeNumber;
    private String Address;
    private String password;
    private Integer Salary;
    private String accountNumber;
    private Date birthDate;
    private String phoneNumber;

    public userObject() {
    }

    public String getName() {
        return name;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getAddress() {
        return Address;
    }

    public Integer getSalary() {
        return Salary;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public userObject(String name, String address, Integer salary, String accountNumber, Date birthDate, String phoneNumber) {
        this.name = name;
        Address = address;
        Salary = salary;
        this.accountNumber = accountNumber;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public userObject(String name, String password)
    {
        this.name = name;
        this.password = password;
    }
}