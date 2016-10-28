package com.avizhen.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Александр on 19.10.2016.
 */
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GenericGenerator(name="CUST_GEN" , strategy="increment")
    @GeneratedValue(generator="CUST_GEN")
    @Column(name = "id")
    private Integer id;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "price")
    private Integer price;

    @Column(name = "date")
    @Type(type = "date")
    private Date date;

    @Column(name = "car_condition")
    private String carCondition;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    Set<CarImage> images = new HashSet<CarImage>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    private Set<Advert> adverts = new HashSet<Advert>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private Set<Item> items = new HashSet<Item>();

    public Car() {
    }

    public Car(String model, String make, Integer price, Date date, String carCondition,
               String description, Set<CarImage> images, Set<Advert> adverts, Set<Item> items) {
        this.model = model;
        this.make = make;
        this.price = price;
        this.date = date;
        this.carCondition = carCondition;
        this.description = description;
        this.images = images;
        this.adverts = adverts;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(String car_condition) {
        this.carCondition = car_condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CarImage> getImages() {
        return images;
    }

    public void setImages(Set<CarImage> images) {
        this.images = images;
    }

    public Set<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(Set<Advert> adverts) {
        this.adverts = adverts;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
