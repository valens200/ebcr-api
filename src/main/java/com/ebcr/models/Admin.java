package com.ebcr.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "admins")
public class Admin extends AppUser {
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "adminId", referencedColumnName = "id")
  private Set<Church> churches;
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "adminId", referencedColumnName = "id")
  private Set<Paroisse> paroisses;
}
