package com.avizhen.repository;

import com.avizhen.entity.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Александр on 15.11.2016.
 */
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    @Query("select a.role from UserRole a, User b where b.userName=?1 and a.user.id=b.id")
    List<String> findRoleByUserName(String username);
}
