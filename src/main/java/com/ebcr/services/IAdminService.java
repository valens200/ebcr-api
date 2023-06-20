package com.ebcr.services;
import com.ebcr.models.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAdminService {

    public Admin createAdmin(Admin admin);
    public List<Admin> findAllAdmins();
}
