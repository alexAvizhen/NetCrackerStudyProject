package com.avizhen.service.impl;

import com.avizhen.entity.User;
import com.avizhen.repository.UserRepository;
import com.avizhen.repository.UserRoleRepository;
import com.avizhen.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Александр on 15.11.2016.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, UserRoleRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("No user present with username: " + s);
        } else {
            List<String> roles = userRoleRepository.findRoleByUserName(s);
            return new CustomUserDetails(user, roles);
        }

    }
}
