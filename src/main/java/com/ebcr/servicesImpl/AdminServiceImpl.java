package com.ebcr.servicesImpl;

import com.ebcr.models.Admin;
import com.ebcr.models.Role;
import com.ebcr.models.User;
import com.ebcr.repositories.AdminRepository;
import com.ebcr.repositories.RoleRepository;
import com.ebcr.services.AdminService;
import com.ebcr.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl  implements AdminService {

    @Autowired
    private MailService mailService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;
    public Admin createAdmin(Admin admin){
        Role role = new Role(com.ebcr.enums.Role.ADMIN, "admin role");
        Role savedRole = roleRepository.save(role);
        admin.getRoles().add(savedRole);
        admin.setRoles(admin.getRoles());
        mailService.sendAccountVerificationEmail(admin);
        return adminRepository.save(admin);
    }
    public List<Admin> findAllAdmins(){
        return adminRepository.findAll();
    }
}
