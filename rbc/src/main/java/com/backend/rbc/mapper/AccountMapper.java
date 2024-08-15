package com.backend.rbc.mapper;

import com.backend.rbc.dtos.AccountDto;
import com.backend.rbc.entities.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getName(),
                accountDto.getCurrency(),
                accountDto.getBalance(),
                accountDto.getTransactions()
        );

        return account;
    }
    
    public static AccountDto mapToDTO(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getName(),
                account.getCurrency(),
                account.getBalance(),
                account.getTransactions()
        );

        return accountDto;
    }
}
