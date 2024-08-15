package com.backend.rbc.services.impl;

import com.backend.rbc.dtos.TransactionDto;
import com.backend.rbc.entities.Account;
import com.backend.rbc.entities.Transaction;
import com.backend.rbc.enums.Type;
import com.backend.rbc.exceptions.*;
import com.backend.rbc.mapper.TransactionMapper;
import com.backend.rbc.repository.AccountRepository;
import com.backend.rbc.repository.TransactionRepository;
import com.backend.rbc.services.TransactionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CurrencySettingsServiceImpl currencySettingsServiceImpl;

    private TransactionMapper transactionMapper;

    @Override
    public List<TransactionDto> getTransactions() {
        List<Transaction> transactionList = transactionRepository.findAll();
        List<TransactionDto> transactionDtos = new ArrayList<>();
        for(Transaction transaction: transactionList){
            transactionDtos.add(transactionMapper.mapToDTO(transaction));
        }

        return transactionDtos;
    }

    @Override
    public List<TransactionDto> getTransactionsForAccount(Long accountId) {
        accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException());

        List<Transaction> transactionList = transactionRepository.findByAccountId(accountId);
        List<TransactionDto> transactionDtos = new ArrayList<>();
        for(Transaction transaction: transactionList){
            transactionDtos.add(transactionMapper.mapToDTO(transaction));
        }

        return transactionDtos;
    }

    @Override
    @Transactional
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Long accountId = transactionDto.getAccount().getId();
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException());

        Transaction transaction = new Transaction();
        if(transactionDto.getDescription().isBlank() || transactionDto.getType().toString().isBlank()){
            throw new InvalidCredentialsException();
        }
        transaction.setDescription(transactionDto.getDescription());
        transaction.setType(transactionDto.getType());
        if(transactionDto.getAmount() <= 0){
            throw new AmountCanNotBeNegativeException();
        }
        transaction.setAmount(transactionDto.getAmount());
        transaction.setAccount(account);

        if(transaction.getType() == Type.EXPENSE){
            if(account.getBalance() < transaction.getAmount()) {
                throw new NotEnoughMoneyException();
            }
            account.setBalance(account.getBalance() - transaction.getAmount());
        } else if (transaction.getType() == Type.PROFIT){
            account.setBalance(account.getBalance() + transaction.getAmount());
        }
        float convertedAmount = currencySettingsServiceImpl.convertToDefaultCurrency(transactionDto.getAmount(), transactionDto.getCurrency());
        transaction.setConvertedAmount(convertedAmount);
        transaction.setCurrency(transactionDto.getCurrency());

        accountRepository.save(account);
        Transaction newTransaction = transactionRepository.save(transaction);

        return transactionMapper.mapToDTO(newTransaction);
    }

    @Override
    public TransactionDto updateTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionRepository.findById(transactionDto.getId())
                .orElseThrow(() -> new TransactionNotFoundException());

        transaction.setDescription(transactionDto.getDescription());
        transaction.setType(transactionDto.getType());
        transaction.setAccount(transactionDto.getAccount());
        transaction.setAmount(transactionDto.getAmount());

        TransactionDto newTransactionDto = transactionMapper.mapToDTO(transaction);

        return newTransactionDto;
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException());
        transactionRepository.deleteById(id);
    }

    @Override
    public void deleteAllTransactions() {
        if(transactionRepository.findAll().isEmpty()){
            throw new NoDataToDeleteException();
        }
        transactionRepository.deleteAll();
    }

}
