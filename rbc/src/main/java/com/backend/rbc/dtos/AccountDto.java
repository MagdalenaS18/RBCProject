package com.backend.rbc.dtos;

import com.backend.rbc.entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String name;
    private String currency;
    private float balance;
    private List<Transaction> transactions;

}
