package com.npickard.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.npickard.Serializer.HouseJSONDeserializer;
import com.npickard.Serializer.HouseJSONSerializer;

/**
 * Created by npickard on 5/2/2017.
 */
@JsonSerialize(using = HouseJSONSerializer.class)
@JsonDeserialize(using = HouseJSONDeserializer.class)
public class House {
    public int streetNumber;
    public String streetName;
    public String cityName;

    public House(){}

    public House(int streetNumber, String streetName){
        this(streetNumber, streetName, null);
    }

    public House(int streetNumber, String streetName, String cityName){
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.cityName = cityName;
    }

    public int getStreetNumber(){
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber){
        this.streetNumber = streetNumber;
    }

    public String getStreetName(){
        return streetName;
    }

    public void setStreetName(String streetName){
        this.streetName = streetName;
    }

    public String getCityName(){
        return  cityName;
    }

    public void setCityName(String cityName){
        this.cityName = cityName;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("\n\t------- HOUSE -----------");
        sb.append("\n\t\tNumber: " + streetNumber);
        sb.append("\n\t\tStreet: " + streetName);
        sb.append("\n\t\tCity: " + cityName);
        sb.append("\n\t----------------------------");
        return sb.toString();
    }
}
