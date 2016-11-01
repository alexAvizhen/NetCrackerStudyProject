package com.avizhen.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Александр on 19.10.2016.
 */
@Entity
@Table(name = "advert")
public class Advert {
    @Id
    @GenericGenerator(name="CUST_GEN" , strategy="increment")
    @GeneratedValue(generator="CUST_GEN")
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "description")
    private String description;


    public Advert() {
    }

    public Advert(Car car, String description) {
        this.car = car;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                //", car=" + car +
                ", description='" + description + '\'' +
                '}';
    }
}
