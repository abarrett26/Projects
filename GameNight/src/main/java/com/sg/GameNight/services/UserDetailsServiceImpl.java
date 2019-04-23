/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.services;

import com.sg.GameNight.dtos.Roles;
import com.sg.GameNight.dtos.Users;
import com.sg.GameNight.daos.GameNightDao;
import com.sg.GameNight.daos.GameNightPersistenceException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author 16128
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    GameNightDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = new Users();
        try {
            user = dao.getUserByUsername(username);
        } catch (GameNightPersistenceException ex) {
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Roles role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}
