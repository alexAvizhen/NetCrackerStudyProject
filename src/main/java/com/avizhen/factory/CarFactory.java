package com.avizhen.factory;

import com.avizhen.entity.Car;

/**
 * Created by Александр on 25.10.2016.
 */
public class CarFactory {
    public static Car createCar(String model, String make) {
        Car resultCar = new Car();
        resultCar.setModel(model);
        resultCar.setMake(make);
        resultCar.setPrice(999);
        resultCar.setCarCondition("used");
        resultCar.setDate(new java.sql.Date(2000, 9, 9));
        return resultCar;
    }

}
