package com.ebcr.controller;

import com.ebcr.dtos.CreateOrUpdateAdminDTO;
import com.ebcr.models.Admin;
import com.ebcr.repositories.AdminRepository;
import com.ebcr.utils.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;
    @PostMapping("create")
    public Admin createAdmin(@RequestBody  CreateOrUpdateAdminDTO adminDTO){
       Admin admin = Mapper.getAdminFromAdminDTO(adminDTO, adminDTO.getPassword());
       return adminRepository.save(admin);
    }


}
