package com.avizhen.entity;

import com.avizhen.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @JsonView(Views.Public.class)
    private Integer id;

    @NotNull
    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "car_id")
    @JsonView(Views.Public.class)
    private Car car;

    @Column(name = "description")
    @JsonView(Views.Public.class)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advert advert = (Advert) o;

        return id.equals(advert.id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + car.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
