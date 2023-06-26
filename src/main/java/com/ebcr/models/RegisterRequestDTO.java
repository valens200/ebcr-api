package com.ebcr.models;

import com.ebcr.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

public class RegisterRequestDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String nationalId;
    private Gender gender;
}
