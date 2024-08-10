package com.backend.rbc.services.impl;

import com.backend.rbc.dtos.AccountDto;
import com.backend.rbc.entities.Account;
import com.backend.rbc.mapper.AccountMapper;
import com.backend.rbc.repository.AccountRepository;
import com.backend.rbc.services.AccountService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
//    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<AccountDto> getAccounts() {
        List<Account> accountList = accountRepository.findAll();
        List<AccountDto> accountDtoList = new ArrayList<>();
        for(Account account : accountList){
            accountDtoList.add(accountMapper.mapToDTO(account));
        }

        return accountDtoList;
    }

    // ici ce u Transaction
    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesn't exist"));

        return accountMapper.mapToDTO(account);
    }

    // probati konstruktor ako ne bude radilo
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        // need to convert accountDto to account (it needs to be JPA entity) to save in the database
        // then I also need to convert account to accountDto to send it again to the client
        Account account = accountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        return accountMapper.mapToDTO(savedAccount);
    }

    @Override
    public boolean deleteAllData() {
        return false;
    }


}
