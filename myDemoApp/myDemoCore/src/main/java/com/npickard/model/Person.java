package com.npickard.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by npickard on 4/30/2017.
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
        StringBuffer sb = new StringBuffer();
        sb.append("\n\t------- PERSON -----------");
        sb.append("\n\t\tName: " + name);
        sb.append("\n\t----------------------------");
        return sb.toString();
    }
}
