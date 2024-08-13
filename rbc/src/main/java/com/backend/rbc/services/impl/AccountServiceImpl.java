package com.backend.rbc.services.impl;

import com.backend.rbc.dtos.AccountDto;
import com.backend.rbc.entities.Account;
import com.backend.rbc.exceptions.AccountNotFoundException;
import com.backend.rbc.exceptions.NoDataToDeleteException;
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
                .orElseThrow(() -> new AccountNotFoundException());

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
                .orElseThrow(() -> new AccountNotFoundException());

        account.setName(accountDto.getName());
        account.setCurrency(accountDto.getCurrency());
        account.setBalance(accountDto.getBalance());
        account.setTransactions(account.getTransactions());

        AccountDto newAccountDto = accountMapper.mapToDTO(account);

        return newAccountDto;
    }

    @Override
    public AccountDto deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException());
        accountRepository.deleteById(id);

        return accountMapper.mapToDTO(account);
    }

    @Override
    public void deleteAllAccounts() {
        if(accountRepository.findAll().isEmpty()){
            new NoDataToDeleteException();
        }
        // dodati EXC za nema vise account-a za brisati
        accountRepository.deleteAll();
    }

}
