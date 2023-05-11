package com.ebcr.controller;

import com.ebcr.dtos.CreateOrUpdateAdminDTO;
import com.ebcr.models.Admin;
import com.ebcr.services.AdminService;
import com.ebcr.utils.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("create")
    public Admin createAdmin(@RequestBody  CreateOrUpdateAdminDTO adminDTO){
       Admin admin = Mapper.getAdminFromAdminDTO(adminDTO, adminDTO.getPassword());
       return adminService.createAdmin(admin);
    }
    @GetMapping("all")
    public List<Admin> getAllAdmins(){
      return this.adminService.findAllAdmins();
    }
}
