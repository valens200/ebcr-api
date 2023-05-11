package com.ebcr.services;
import com.ebcr.models.Admin;
import com.ebcr.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public Admin createAdmin(Admin admin){
        return adminRepository.save(admin);
    }
    public List<Admin> findAllAdmins(){
        return adminRepository.findAll();
    }
}
