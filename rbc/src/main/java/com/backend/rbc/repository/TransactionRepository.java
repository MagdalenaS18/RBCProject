package com.backend.rbc.repository;

import com.backend.rbc.dtos.AccountDto;
import com.backend.rbc.entities.Account;
import com.backend.rbc.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Custom query to find transactions by accountId
//    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId")


//    @Query("SELECT t FROM transaction t WHERE t.account_id = :accountId")
//    List<Transaction> findByAccount(@Param("accountsTransaction") Long accountsTransaction);

    List<Transaction> findByAccountId(Long accountId);

//    @Query("SELECT t.account_id FROM transaction t")
//    Long findAccountId(@Param("accountId") Long accountId);
}
