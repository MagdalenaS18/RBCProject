package com.backend.rbc.controllers;

import com.backend.rbc.dtos.TransactionDto;
import com.backend.rbc.entities.Account;
import com.backend.rbc.services.TransactionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@AllArgsConstructor
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getTransactions(){
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDto>> getTransactionsForAccount(@PathVariable Long accountId){
        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId));
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto){
        return new ResponseEntity<>(transactionService.createTransaction(transactionDto), HttpStatus.CREATED);
    }

//    @Autowired
//    private TransactionService transactionService;
//
//    @GetMapping("/account/{accountId}")
//    public List<TransactionDto> getTransactionsByAccountId(@PathVariable Long accountId) {
//        return transactionService.getTransactionsByAccountId(accountId);
//    }
}
