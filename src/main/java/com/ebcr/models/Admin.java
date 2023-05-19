package com.ebcr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "admins")
public class Admin extends User{
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
