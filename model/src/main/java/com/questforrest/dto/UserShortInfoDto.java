package com.questforrest.dto;

import java.io.Serializable;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
public class UserShortInfoDto implements Serializable {
    private String name;
    private String surname;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}