package com.ebcr.services;
import com.ebcr.models.Admin;
import com.ebcr.models.Role;
import com.ebcr.repositories.AdminRepository;
import com.ebcr.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    public Admin createAdmin(Admin admin);
    public List<Admin> findAllAdmins();
}
