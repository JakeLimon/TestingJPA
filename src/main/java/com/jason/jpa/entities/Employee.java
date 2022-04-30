package com.jason.jpa.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// Con esta entidad vamos a generar el id de manera aleatoria y de manera customizada

@Entity
@Table( name = "empleado")
public class Employee {

    @Id
    @GenericGenerator(name = "empl_id", strategy = "com.jason.jpa.idgeneratos.CustomRandomIDGenerator")
    @GeneratedValue( generator = "empl_id" )
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
