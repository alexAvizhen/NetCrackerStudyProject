package com.avizhen.entity;


import com.avizhen.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    @JsonView(Views.Public.class)
    private Integer id;

    @Column(name = "car_image", nullable = false)
    @JsonView(Views.Public.class)
    private String carImagePath;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "car_id")
    private Car car;

    public CarImage() {
    }

    public CarImage(String carImagePath, Car car) {
        this.carImagePath = carImagePath;
        this.car = car;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarImagePath() {
        return carImagePath;
    }

    public void setCarImagePath(String carImagePath) {
        this.carImagePath = carImagePath;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "CarImage{" +
                "id=" + id +
                ", carImagePath='" + carImagePath + '\'' +
                ", car=" + car +
                '}';
    }
}
