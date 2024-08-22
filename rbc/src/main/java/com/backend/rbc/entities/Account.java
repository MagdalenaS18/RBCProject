package com.backend.rbc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "currency")
    private String currency;

    @Column(name = "balance")
    private float balance;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions;

//    public Account(Long id, String name, String currency, float balance, List<Transaction> transactions) {
//        this.id = id;
//        this.name = name;
//        this.currency = currency;
//        this.balance = balance;
//        this.transactions = this.getTransactions();
//    }

    //private List<Transaction> transactions;
}
