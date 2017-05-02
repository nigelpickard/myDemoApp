package com.npickard.model;

import javax.persistence.*;


/**
 * Created by npickard on 4/30/2017.
 */
@Entity
@Table(name="FlattenedHouse")
public class FlattenedHouse {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private int streetNumber;
    private String streetName;
    private String cityName;

    public FlattenedHouse(){}

    public FlattenedHouse(House house){
        this.streetNumber = house.getStreetNumber();
        this.streetName = "flattened " + house.getStreetName();
        //this.cityName = house.getCityName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("\n\t--------- FLATTENED HOUSE ----------");
        sb.append("\n\t\tNumber: " + streetNumber);
        sb.append("\n\t\tStreet: " + streetName);
        //sb.append("\n\t\tCity: " + cityName);
        sb.append("\n\t----------------------------------");
        return sb.toString();
    }
}
