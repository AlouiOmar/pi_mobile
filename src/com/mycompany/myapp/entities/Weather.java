/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Administrateur
 */
public class Weather {
    private String city,wdate,temperature,description;

    public Weather(String city, String wdate, String temperature, String description) {
        this.city = city;
        this.wdate = wdate;
        this.temperature = temperature;
        this.description = description;
    }

    public Weather() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWdate() {
        return wdate;
    }

    public void setWdate(String wdate) {
        this.wdate = wdate;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" + "city=" + city + ", wdate=" + wdate + ", temperature=" + temperature + ", description=" + description + '}';
    }
    



 
}