package com.example.secondproj.security.service;

import com.example.secondproj.security.Repositories.AppRoleRepository;
import com.example.secondproj.security.Repositories.AppUserRepository;
import com.example.secondproj.security.entities.AppRole;
import com.example.secondproj.security.entities.AppUser;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    PasswordEncoder passEncoder;
    AppUserRepository userRepository;
    AppRoleRepository roleRepository;
    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Password not match");

        String hashedPass=passEncoder.encode(password);
        AppUser user=new AppUser();
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername(username);
        System.out.println(hashedPass);
        user.setPassword(hashedPass);
        user.setActive(true);
        AppUser saveduser=userRepository.save(user);
        return saveduser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {

        AppRole appRole=new AppRole();
        AppRole role= roleRepository.findByRoleName(roleName);
        if(role!=null)
            throw new RuntimeException("Already exist");

        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole= roleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        AppUser appUser=userRepository.findByUsername (username);
        if (appUser==null) throw new RuntimeException ("User not found");
        AppRole appRole=roleRepository.findByRoleName (roleName);
        if (appRole==null) throw new RuntimeException ("Role not found");
        appUser.getAppRoles().add(appRole);
        userRepository.save(appUser);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser=userRepository.findByUsername (username);
        if (appUser==null) throw new RuntimeException ("User not found");
        AppRole appRole=roleRepository.findByRoleName (roleName);
        if (appRole==null) throw new RuntimeException ("Role not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser LoadUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
