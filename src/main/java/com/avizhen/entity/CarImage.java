package com.avizhen.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by Александр on 25.10.2016.
 */
@Entity
@Table(name = "carimage")
public class CarImage {

    @Id
    @GenericGenerator(name = "CUST_GEN", strategy = "increment")
    @GeneratedValue(generator = "CUST_GEN")
    @Column(name = "id")
    private Integer id;

    @Column(name = "car_image", nullable = false)
    private Blob carImage;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "car_id")
    private Car car;

    public CarImage() {
    }

    public CarImage(Blob carImage, Car car) {
        this.carImage = carImage;
        this.car = car;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Blob getCarImage() {
        return carImage;
    }

    public void setCarImage(Blob carImage) {
        this.carImage = carImage;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
