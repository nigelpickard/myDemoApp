package com.npickard.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by npickard on 4/30/2017.
 */
@Entity
@Table(name="Car")
public class Car implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String manufacturer;
    private String model;
    private TransmissionType transmissionType;

    public Car(){}

    public Car(String manufacturer){
        this.manufacturer = manufacturer;
    }

    public Car(String manufacturer, String model, TransmissionType transmissionType){
        this.manufacturer = manufacturer;
        this.model = model;
        this.transmissionType = transmissionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    /**/
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("\n\t--------- CAR -----------");
        sb.append("\n\t\tManufacturer: " + manufacturer);
        sb.append("\n\t\tModel: " + model);
        sb.append("\n\t\tTransmission: " + transmissionType);
        sb.append("\n\t-------------------------");
        return sb.toString();
    }
}
