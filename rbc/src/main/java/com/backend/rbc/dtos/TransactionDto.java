package com.backend.rbc.dtos;

import com.backend.rbc.entities.Account;
import com.backend.rbc.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    private String description;
    private Type type;
    private Account account;
    private Float amount;
    private String currency;
}
