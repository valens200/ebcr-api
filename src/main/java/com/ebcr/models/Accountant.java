package com.ebcr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accountant  extends User{
    @OneToOne
    private Church church;
    @OneToMany
    @JoinColumn(
            name = "accountantId",
            referencedColumnName = "id"
    )
    private Set<Notification> notifications;
    @OneToMany
    @JoinColumn(
            name = "accountantId",
            referencedColumnName = "id"
    )
    private Set<Versement>  versements;

}
