package com.ebcr.servicesImpl;

import com.ebcr.models.AppUser;
import com.ebcr.models.Role;
import com.ebcr.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl  implements IUserService {

    @Override
    public AppUser registerUser(AppUser user) {
        return null;
    }

    @Override
    public Role registerRole(Role role) {
        return null;
    }

    @Override
    public AppUser addRoleTOUser(String email, String roleName) {
        return null;
    }

    @Override
    public AppUser findByEmail(String email) {
        return null;
    }

    @Override
    public Collection<AppUser> getAllUsers() {
        return null;
    }

    @Override
    public Collection<Role> getAllRoles() {
        return null;
    }
}
