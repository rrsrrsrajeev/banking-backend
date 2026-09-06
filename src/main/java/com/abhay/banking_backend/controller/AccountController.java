package com.abhay.banking_backend.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhay.banking_backend.dto.AccountRequest;
import com.abhay.banking_backend.dto.AccountResponse;
import com.abhay.banking_backend.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers/{customerId}/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponse createAccount(
            @PathVariable Long customerId,
            @Valid @RequestBody AccountRequest request) {

        return accountService.createAccount(
                customerId,
                request);
    }
}