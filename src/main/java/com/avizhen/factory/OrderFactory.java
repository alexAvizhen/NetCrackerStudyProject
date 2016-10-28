package com.avizhen.factory;

import com.avizhen.entity.Order;

import java.sql.Date;

/**
 * Created by Александр on 25.10.2016.
 */
public class OrderFactory {
    public static Order createOrder(String status) {
        Order resultOrder = new Order();
        resultOrder.setRegistrationDate(new Date(21312321));
        resultOrder.setStatus(status);
        return resultOrder;
    }
}
