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
@Table(name = "userorder")
public class Order {
    @Id
    @GenericGenerator(name = "CUST_GEN", strategy = "increment")
    @GeneratedValue(generator = "CUST_GEN")
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reg_date")
    @Type(type = "date")
    private Date registrationDate;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy="userOrder", cascade ={CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Item> items = new HashSet<Item>();

    public Order() {
    }

    public Order(User user, Date registrationDate, String status, Set<Item> items) {
        this.user = user;
        this.registrationDate = registrationDate;
        this.status = status;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
