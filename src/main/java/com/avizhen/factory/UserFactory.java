package com.avizhen.factory;

import com.avizhen.entity.User;
import com.avizhen.enums.UserRoleEnum;

/**
 * Created by Александр on 25.10.2016.
 */
public class UserFactory {
    public static User createUser(String name, String surname, UserRoleEnum role) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail("testmail@yandex.ru");
        user.setRole(UserRoleFactory.getUserRole(role));
        user.setLogin("testlogin");
        user.setPassword("test");
        return user;

    }
}
