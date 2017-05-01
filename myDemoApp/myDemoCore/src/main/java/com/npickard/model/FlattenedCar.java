package com.npickard.model;

import com.npickard.Serializer.SerializerType;

import javax.persistence.*;


/**
 * Created by npickard on 4/30/2017.
 */
@Entity
@Table(name="FlattenedCar")
public class FlattenedCar {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String manufacturer;
    private String model;
    private TransmissionType transmissionType;

    public FlattenedCar(){}

    public FlattenedCar(Car car){
        this.manufacturer = car.getManufacturer();
        this.model = car.getModel();
        this.transmissionType = car.getTransmissionType();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return "flattened " + manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel(){
        return "flattened " + model;
    }

    public void setModel(String model){
        this.model = model;
    }

    public TransmissionType getTransmissionType(){
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType){
        this.transmissionType = transmissionType;
    }

    @Override
    public String toString(){
        return "manufacturer=" + manufacturer;
    }
}
