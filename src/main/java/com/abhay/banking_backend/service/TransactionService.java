package com.abhay.banking_backend.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhay.banking_backend.dto.TransferRequest;
import com.abhay.banking_backend.entity.Account;
import com.abhay.banking_backend.entity.Transaction;
import com.abhay.banking_backend.repository.AccountRepository;
import com.abhay.banking_backend.repository.TransactionRepository;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction transfer(
            Long fromAccountId,
            TransferRequest request) {

        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Source account not found"));

        Account toAccount = accountRepository
                .findByAccountNumber(request.getToAccountNumber())
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        if (fromAccount.getId().equals(toAccount.getId())) {
            throw new RuntimeException(
                    "Cannot transfer to the same account");
        }

        if (!"ACTIVE".equals(fromAccount.getStatus())) {
            throw new RuntimeException(
                    "Source account is not active");
        }

        if (!"ACTIVE".equals(toAccount.getStatus())) {
            throw new RuntimeException(
                    "Destination account is not active");
        }

        if (fromAccount.getBalance()
                .compareTo(request.getAmount()) < 0) {

            throw new RuntimeException(
                    "Insufficient balance");
        }

        // Debit source account
        fromAccount.setBalance(
                fromAccount.getBalance()
                        .subtract(request.getAmount()));

        // Credit destination account
        toAccount.setBalance(
                toAccount.getBalance()
                        .add(request.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Create transaction record
        Transaction transaction = new Transaction();

        transaction.setTransactionReference(
                UUID.randomUUID().toString());

        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(request.getAmount());
        transaction.setType("TRANSFER");
        transaction.setStatus("COMPLETED");
        transaction.setCreatedAt(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
}