package com.abhay.banking_backend.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhay.banking_backend.dto.TransferRequest;
import com.abhay.banking_backend.entity.Transaction;
import com.abhay.banking_backend.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(
            TransactionService transactionService) {

        this.transactionService = transactionService;
    }

    @PostMapping("/{accountId}/transfer")
    public Transaction transfer(
            @PathVariable Long accountId,
            @Valid @RequestBody TransferRequest request) {

        return transactionService.transfer(
                accountId,
                request);
    }
}