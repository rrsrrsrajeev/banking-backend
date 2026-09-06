package com.abhay.banking_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponse {

    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private String status;
    private Long customerId;
    private LocalDateTime createdAt;

    public AccountResponse(
            Long id,
            String accountNumber,
            BigDecimal balance,
            String status,
            Long customerId,
            LocalDateTime createdAt) {

        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.status = status;
        this.customerId = customerId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}