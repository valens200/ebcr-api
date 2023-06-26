package com.ebcr.servicesImpl;

import com.ebcr.models.AppUser;
import com.ebcr.models.Role;
import com.ebcr.repositories.RoleRepository;
import com.ebcr.repositories.UserRepository;
import com.ebcr.services.UserService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{

            AppUser user = userRepository.findByEmail(username);
            log.info("user {}", user);
            Collection<Role> roles = user.getRoles();
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            roles.stream().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName().toString()));
            });
            return new User(user.getEmail(), user.getPassword(), authorities);
        }catch(Exception exception){
            log.error("error : {}", exception.getMessage());
            return null;
        }
    }

    @Override
    public AppUser registerUser(AppUser user) {
        return userRepository.save(user);
    }
    @Override
    public Role registerRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public AppUser addRoleTOUser(String email, String roleName) {
        return null;
    }

    @Override
    public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Collection<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Collection<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
