package com.appsforusers.agendalite.model;

/**
 * Created by preto on 06/03/2018.
 */

public class Developer {

    private String name;
    private String position;
    private int age;


    public Developer(){

    }

    public Developer(String name, String position, int age) {
        this.name = name;
        this.position = position;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
