package com.backend.rbc.controllers;

import com.backend.rbc.dtos.AccountDto;
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

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto){
        return new ResponseEntity<>(transactionService.createTransaction(transactionDto), HttpStatus.CREATED);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDto>> getTransactionsByAccountId(@PathVariable Long accountId){
        return ResponseEntity.ok(transactionService.getTransactionsForAccount(accountId));
    }

    @PatchMapping
    public ResponseEntity updateTransaction(@RequestBody TransactionDto transactionDto){
        transactionService.updateTransaction(transactionDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }


//    @GetMapping("/account/{accountId}")
//    public ResponseEntity<List<TransactionDto>> getTransactionsForAccount(@RequestBody AccountDto accountDto, @PathVariable Long accountId){
//        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountDto));
//    }



//    @GetMapping("/account/{accountId}")
//    public List<TransactionDto> getTransactionsByAccountId(@PathVariable Long accountId) {
//        return transactionService.getTransactionsByAccountId(accountId);
//    }
}
