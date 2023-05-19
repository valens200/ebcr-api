package com.ebcr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConsumerBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID consumerBudgetId;

}
