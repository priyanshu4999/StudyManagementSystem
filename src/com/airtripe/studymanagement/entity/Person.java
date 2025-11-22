package com.airtripe.studymanagement.entity;

import com.airtripe.studymanagement.util.DateUtil;

public abstract class Person {
    private String name;
    private String id;
    private String email;
    private String phone;
    private int age;

    public Person(String name , String id){}
    public Person(String name, String id, String email, String phone, int age) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public abstract String getRole();




}

