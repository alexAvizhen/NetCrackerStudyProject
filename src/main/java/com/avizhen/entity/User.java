package com.avizhen.entity;

import com.avizhen.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Александр on 19.10.2016.
 */

@Entity
@Table(name = "user")
public class User {

    @Id
    @GenericGenerator(name="CUST_GEN" , strategy="increment")
    @GeneratedValue(generator="CUST_GEN")
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "username", nullable = false)
    private String userName;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @JsonView(Views.Public.class)
    @Column(name = "surname", nullable = false)
    private String surname;

    @Email
    @JsonView(Views.Public.class)
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address")
    private String address;

    @JsonView(Views.Public.class)
    @Column(name = "phone")
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "user")
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private Set<Order> orders = new HashSet<Order>();

    public User() {
    }

    public User(User user) {
        id = user.getId();
        userName = user.getUserName();
        password = user.getPassword();
        firstName = user.getFirstName();
        surname = user.getSurname();
        email = user.getEmail();
        address = user.getAddress();
        phone = user.getPhone();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + roles +
                '}';
    }
}
