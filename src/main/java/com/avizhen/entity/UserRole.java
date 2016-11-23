package com.avizhen.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Александр on 25.10.2016.
 */
@Entity
@Table(name = "role")
public class UserRole {

    @Id
    @GenericGenerator(name="CUST_GEN" , strategy="increment")
    @GeneratedValue(generator="CUST_GEN")
    @Column(name = "id")
    private Integer id;

    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserRole() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
