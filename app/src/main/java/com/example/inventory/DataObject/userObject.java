package com.example.inventory.DataObject;

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

    public userObject(String name, String address, Integer salary, String accountNumber, Date birthDate, String phoneNumber) {
        this.name = name;
        Address = address;
        Salary = salary;
        this.accountNumber = accountNumber;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public userObject(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getSalary() {
        return Salary;
    }

    public void setSalary(Integer salary) {
        Salary = salary;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
