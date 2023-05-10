package com.ebcr.utils;

import com.ebcr.models.Admin;
import com.ebcr.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Mapper {

    public static ModelMapper modelMapper = new ModelMapper();
    public  static  final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static String encode(String password){
        return passwordEncoder.encode(password);
    }

    public static User getUserFromDTO(Object object, String password){
      User user = getUserFromDTO(object);
       user.setPassword(passwordEncoder.encode(password));
       user.setId(null);
       return user;
    }
    public  static User getUserFromDTO(Object object){
        return modelMapper.map(object, User.class);
    }
    public  static Admin getAdminFromAdminDTO(Object object, String password){
        Admin admin = modelMapper.map(object, Admin.class);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return admin;
    }
}
