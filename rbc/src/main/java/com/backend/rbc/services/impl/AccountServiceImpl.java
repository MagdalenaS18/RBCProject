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

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

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

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesn't exist"));

        return accountMapper.mapToDTO(account);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        // need to convert accountDto to account (it needs to be JPA entity) to save in the database
        // then I also need to convert account to accountDto to send it again to the client
        Account account = accountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        return accountMapper.mapToDTO(savedAccount);
    }

    @Override
    public AccountDto updateAccount(AccountDto accountDto) {
        Account account = accountRepository.findById(accountDto.getId())
                .orElseThrow(() -> new RuntimeException("Account doesn't exist!"));

        account.setName(accountDto.getName());
        account.setCurrency(accountDto.getCurrency());
        account.setBalance(accountDto.getBalance());
        account.setTransactions(account.getTransactions());

        AccountDto newAccountDto = accountMapper.mapToDTO(account);

        return newAccountDto;
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesn't exist"));
        accountRepository.deleteById(id);
    }

    @Override
    public void deleteAllAccounts() {
        if(accountRepository.findAll().isEmpty()){
            new RuntimeException("There's no account to delete!");
        }
        accountRepository.deleteAll();
    }

}
