package com.backend.rbc.services;

import com.backend.rbc.dtos.TransactionDto;
import com.backend.rbc.entities.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    List<TransactionDto> getTransactions();
//    List<TransactionDto> getTransactionsByAccountId(Long id);
    List<TransactionDto> getTransactionsByAccountId(Long accountId);
    TransactionDto createTransaction(TransactionDto transactionDto);

}
