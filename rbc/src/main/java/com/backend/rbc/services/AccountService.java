package com.backend.rbc.services;

import com.backend.rbc.dtos.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAccounts();
    AccountDto getAccountById(Long id);
    AccountDto createAccount(AccountDto accountDto);
    boolean deleteAllData();
}
