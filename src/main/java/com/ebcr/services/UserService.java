package com.ebcr.services;

import com.ebcr.models.AppUser;
import com.ebcr.models.Role;

import java.util.Collection;

public interface UserService {
    public AppUser registerUser(AppUser user);
    public Role registerRole(Role role);
    public  AppUser addRoleTOUser(String email, String roleName);
    public  AppUser findByEmail(String email);
    public Collection<AppUser> getAllUsers();
    public  Collection<Role> getAllRoles();
}
