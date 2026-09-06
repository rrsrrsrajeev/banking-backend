package com.abhay.banking_backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.abhay.banking_backend.dto.AccountRequest;
import com.abhay.banking_backend.dto.AccountResponse;
import com.abhay.banking_backend.entity.Account;
import com.abhay.banking_backend.entity.Customer;
import com.abhay.banking_backend.repository.AccountRepository;
import com.abhay.banking_backend.repository.CustomerRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(
            AccountRepository accountRepository,
            CustomerRepository customerRepository) {

        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public AccountResponse createAccount(
            Long customerId,
            AccountRequest request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException(
                        "Customer not found"));

        Account account = new Account();

        account.setAccountNumber(
                String.valueOf(System.currentTimeMillis()));

        account.setBalance(request.getInitialBalance());
        account.setStatus("ACTIVE");
        account.setCustomer(customer);
        account.setCreatedAt(LocalDateTime.now());

        Account savedAccount = accountRepository.save(account);

        return mapToResponse(savedAccount);
    }

    private AccountResponse mapToResponse(Account account) {

        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getStatus(),
                account.getCustomer().getId(),
                account.getCreatedAt());
    }
}