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

    public FlattenedCar(){}

    public FlattenedCar(Car car){
        this(car.getManufacturer());
    }

    public FlattenedCar(String manufacturer){
        this.manufacturer = "flattened "+ manufacturer + " " + SerializerType.CASTOR;
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

    @Override
    public String toString(){
        return "manufacturer=" + manufacturer;
    }
}
