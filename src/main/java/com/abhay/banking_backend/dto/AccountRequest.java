package com.abhay.banking_backend.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class AccountRequest {

    @NotNull(message = "Initial balance is required")
    @DecimalMin(value = "0.00", message = "Initial balance cannot be negative")
    private BigDecimal initialBalance;

    public AccountRequest() {
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }
}