package com.avizhen.repository;

import com.avizhen.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Александр on 25.10.2016.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    User findByUserName(String username);

    @Query("from User")
    List<User> getAll();

}

