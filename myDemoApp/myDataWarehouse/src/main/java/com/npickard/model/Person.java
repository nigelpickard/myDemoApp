package com.npickard.model;

import javax.jms.Message;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by npickard on 2/22/2017.
 */
@Entity
@Table(name="Person")
public class Person implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;

    public Person(){}

    public Person(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "name=" + name;
    }
}
