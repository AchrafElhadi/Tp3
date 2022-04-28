package com.example.secondproj.security.service;

import com.example.secondproj.security.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SecurityService secService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appuser= secService.LoadUserByUserName(username);
        Collection<GrantedAuthority> grantedAuthorities=new ArrayList();
        appuser.getAppRoles().forEach(v->{
            SimpleGrantedAuthority grantauth= new SimpleGrantedAuthority(v.getRoleName());
            grantedAuthorities.add(grantauth);
        });
        User user=new User(appuser.getUsername(),appuser.getPassword(),grantedAuthorities);
        return  user;
    }
}
