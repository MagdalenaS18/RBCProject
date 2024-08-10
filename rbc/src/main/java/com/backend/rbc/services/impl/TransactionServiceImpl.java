package com.backend.rbc.services.impl;

import com.backend.rbc.dtos.TransactionDto;
import com.backend.rbc.entities.Account;
import com.backend.rbc.entities.Transaction;
import com.backend.rbc.enums.Type;
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
    public List<TransactionDto> getTransactionsByAccountId(Long accountId) {
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
//        Transaction transactions = transactionRepository.findByAccountId(transactionDto.getAccountId());

        Transaction transaction = transactionMapper.mapToTransaction(transactionDto);
        Account account = accountRepository.findById(transaction.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if(transaction.getType() == Type.EXPENSE){
            if(account.getBalance() < transaction.getAmount()) {
                new RuntimeException("You don't have enough money to make this transaction!");
            }
            account.setBalance(account.getBalance() - transaction.getAmount());
        } else if (transaction.getType() == Type.PROFIT){
            account.setBalance(account.getBalance() + transaction.getAmount());
        }

        accountRepository.save(account);
        Transaction newTransaction = transactionRepository.save(transaction);

        return transactionMapper.mapToDTO(newTransaction);
    }

//    @Autowired
//    private TransactionRepository transactionRepository;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    public List<TransactionDto> getTransactionsByAccountId(Long accountId) {
//        // Retrieve the account by ID
//        Account account = accountRepository.findById(accountId)
//                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));
//
//        // Find transactions by account
//        List<Transaction> transactions = transactionRepository.findByAccount(account);
//
//        // Convert Transaction entities to DTOs (assuming you have a method to convert entities to DTOs)
//        return transactions.stream()
//                .map(this::convertToDto) // Implement this method to map Transaction to TransactionDto
//                .collect(Collectors.toList());
//    }
//
//    private TransactionDto convertToDto(Transaction transaction) {
//        // Convert the Transaction entity to a TransactionDto
//        TransactionDto dto = new TransactionDto();
//        dto.setId(transaction.getId());
//        dto.setDescription(transaction.getDescription());
//        dto.setType(transaction.getType());
//        dto.setAmount(transaction.getAmount());
//        // Add more fields as necessary
//        return dto;
//    }
}
