package com.ebcr.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Versement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID versementId;

}
