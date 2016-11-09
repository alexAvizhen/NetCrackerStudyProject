package com.avizhen.restws.entity;

import com.avizhen.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Date;


/**
 * Created by Александр on 28.10.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {
    @JsonView(Views.Public.class)
    public Integer Cur_ID;
    @JsonView(Views.Public.class)
    public Date Date;
    @JsonView(Views.Public.class)
    public String Cur_Abbreviation;
    @JsonView(Views.Public.class)
    public Integer Cur_Scale;
    @JsonView(Views.Public.class)
    public String Cur_Name;
    @JsonView(Views.Public.class)
    public double Cur_OfficialRate;

    public Rate() {
    }

    public Integer getCur_ID() {
        return Cur_ID;
    }

    public void setCur_ID(Integer cur_ID) {
        Cur_ID = cur_ID;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public String getCur_Abbreviation() {
        return Cur_Abbreviation;
    }

    public void setCur_Abbreviation(String cur_Abbreviation) {
        Cur_Abbreviation = cur_Abbreviation;
    }

    public Integer getCur_Scale() {
        return Cur_Scale;
    }

    public void setCur_Scale(Integer cur_Scale) {
        Cur_Scale = cur_Scale;
    }

    public String getCur_Name() {
        return Cur_Name;
    }

    public void setCur_Name(String cur_Name) {
        Cur_Name = cur_Name;
    }

    public double getCur_OfficialRate() {
        return Cur_OfficialRate;
    }

    public void setCur_OfficialRate(double cur_OfficialRate) {
        Cur_OfficialRate = cur_OfficialRate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "Cur_ID=" + Cur_ID +
                ", date=" + Date +
                ", Cur_Abbreviation='" + Cur_Abbreviation + '\'' +
                ", Cur_Scale=" + Cur_Scale +
                ", Cur_Name='" + Cur_Name + '\'' +
                ", Cur_OfficialRate=" + Cur_OfficialRate +
                '}';
    }
}
