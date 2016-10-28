package com.avizhen.factory;

import com.avizhen.entity.UserRole;
import com.avizhen.enums.UserRoleEnum;

/**
 * Created by Александр on 25.10.2016.
 */
public class UserRoleFactory {
    private static UserRole GUEST = new UserRole();
    private static UserRole USER = new UserRole();
    private static UserRole ADMIN = new UserRole();
    static{
        GUEST.setId(1);
        GUEST.setRole("GUEST");
        USER.setId(2);
        USER.setRole("USER");
        ADMIN.setId(3);
        ADMIN.setRole("ADMIN");
    }


    public static UserRole getUserRole(UserRoleEnum role) {
        if (UserRoleEnum.GUEST.equals(role)) {
            return GUEST;
        }
        if (UserRoleEnum.USER.equals(role)) {
            return USER;
        }
        if (UserRoleEnum.ADMIN.equals(role)) {
            return ADMIN;
        }
        return null;
    }
}
