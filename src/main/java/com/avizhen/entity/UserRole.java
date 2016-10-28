package com.avizhen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Александр on 25.10.2016.
 */
@Entity
@Table(name = "role")
public class UserRole {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "role")
    private String role;

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
}
