package com.backend.rbc.services.impl;

import com.backend.rbc.dtos.TransactionDto;
import com.backend.rbc.entities.Account;
import com.backend.rbc.entities.Settings;
import com.backend.rbc.entities.Transaction;
import com.backend.rbc.enums.Type;
import com.backend.rbc.exceptions.*;
import com.backend.rbc.mapper.TransactionMapper;
import com.backend.rbc.output.CurrencyResponse;
import com.backend.rbc.repository.AccountRepository;
import com.backend.rbc.repository.TransactionRepository;
import com.backend.rbc.services.TransactionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
//    @Autowired
//    private RestTemplate restTemplate;

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

        float convertedAmount = convertToDefaultCurrency(transactionDto.getAmount(), transactionDto.getCurrency(), accountId);
        if(transaction.getType() == Type.EXPENSE){
            if(account.getBalance() < convertedAmount) {
                throw new NotEnoughMoneyException();
            }
            account.setBalance(account.getBalance() - convertedAmount);
        } else if (transaction.getType() == Type.PROFIT){
            account.setBalance(account.getBalance() + convertedAmount);
        }
//        float convertedAmount = currencySettingsServiceImpl.convertToDefaultCurrency(transactionDto.getAmount(), transactionDto.getCurrency());
        //transaction.setConvertedAmount(convertedAmount);
        transaction.setCurrency(transactionDto.getCurrency().toLowerCase());

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
        transaction.setCurrency(transactionDto.getCurrency().toLowerCase());

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

    public float convertToDefaultCurrency(float amount, String transactionCurrency, Long accountId) {
//        String defaultCurrency = getDefaultCurrency().toUpperCase();
//        Settings settings = settingsRepository.findAll().stream().findFirst().orElseThrow(() ->
//                new RuntimeException("Default currency not set in settings"));
//        String defaultCurrency = settings.getDefaultCurrency().toUpperCase();
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new AccountNotFoundException());

        if (transactionCurrency.equals(account.getCurrency())) {
            return amount;
        }

        String apiUrl = String.format("https://latest.currency-api.pages.dev/v1/currencies/%s.json", transactionCurrency.toLowerCase());
        System.out.println("\t\t api: " + apiUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CurrencyResponse> response = restTemplate.getForEntity(apiUrl, CurrencyResponse.class);
        System.out.println("\t\t response: " + response);

        if (response.getStatusCode().is2xxSuccessful()) {
            CurrencyResponse currencyResponse = response.getBody();
            System.out.println("\t\t response: " + currencyResponse);
            Map<String, Float> rates = currencyResponse.getRatesForBaseCurrency(transactionCurrency);

            Float conversionRate = rates.get(account.getCurrency().toLowerCase());
            if (conversionRate != null) {
                return amount * conversionRate;
            }
        }

        throw new UnableToFetchCurrenciesException();
    }

}
