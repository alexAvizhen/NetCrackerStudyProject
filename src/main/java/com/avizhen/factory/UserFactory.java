package com.avizhen.factory;

import com.avizhen.entity.User;

/**
 * Created by Александр on 25.10.2016.
 */
public class UserFactory {
    public static User createUser(String name, String surname) {
        User user = new User();
        user.setFirstName(name);
        user.setSurname(surname);
        user.setEmail("testmail@yandex.ru");
        user.setUserName("testlogin");
        user.setPassword("test");
        return user;

    }
}
