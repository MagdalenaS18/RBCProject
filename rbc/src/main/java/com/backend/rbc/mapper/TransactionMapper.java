package com.backend.rbc.mapper;

import com.backend.rbc.dtos.TransactionDto;
import com.backend.rbc.entities.Transaction;

public class TransactionMapper {
    public static Transaction mapToTransaction(TransactionDto transactionDto){
        Transaction transaction = new Transaction(
                transactionDto.getId(),
                transactionDto.getDescription(),
                transactionDto.getType(),
                transactionDto.getAccount(),
                transactionDto.getAmount(),
                transactionDto.getConvertedAmount(),
                transactionDto.getCurrency()
        );

        return transaction;
    }

    public static TransactionDto mapToDTO(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getType(),
                transaction.getAccount(),
                transaction.getAmount(),
                transaction.getConvertedAmount(),
                transaction.getCurrency()
        );

        return transactionDto;
    }
}
