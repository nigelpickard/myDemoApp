package com.npickard.model;

import com.npickard.Serializer.SerializerType;

import javax.persistence.*;

/**
 * Created by npickard on 2/22/2017.
 */
@Entity
@Table(name="FlattenedPerson")
public class FlattenedPerson {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;

    public FlattenedPerson(){}

    public FlattenedPerson(Person person){
        this(person.getName());
    }

    public FlattenedPerson(String name){
        this.name = "flattened "+ name + " " + SerializerType.JAXB;
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
