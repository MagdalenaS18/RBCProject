package com.backend.rbc.entities;

import com.backend.rbc.enums.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    C:\Users\HP\Desktop\rbc
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    @NotNull(message = "Description cannot be null")
    private String description;

    @Column(name = "type")
    @NotNull(message = "Type cannot be null")
    private Type type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @NotNull(message = "AccountId cannot be null")
    private Account accountId;

    @Column(name = "amount")
    @NotNull(message = "Amount cannot be null")
    private double amount;

    //List<Transaction> allTransactions;
}
