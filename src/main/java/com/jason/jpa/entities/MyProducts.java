package com.jason.jpa.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MyProducts {

    @Id
    @GenericGenerator(name = "random_mio", strategy = "com.jason.jpa.idgeneratos.GeneradorCustomizado")
    @GeneratedValue(generator = "random_mio")
    private Integer id;

    @Column( name = "name")
    private String name;

    @Column( name = "descripcion" )
    private String desc;

    @Column(name = "precio")
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
