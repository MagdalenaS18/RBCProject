package com.backend.rbc.services;

import com.backend.rbc.dtos.AccountDto;
import com.backend.rbc.dtos.TransactionDto;
import com.backend.rbc.entities.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    List<TransactionDto> getTransactions();
    List<TransactionDto> getTransactionsForAccount(Long accountId);
    TransactionDto createTransaction(TransactionDto transactionDto);
    TransactionDto updateTransaction(TransactionDto transactionDto);
    void deleteTransaction(Long id);
    void deleteAllTransactions();

    float convertToDefaultCurrency(float amount, String transactionCurrency, Long accountId);

}
