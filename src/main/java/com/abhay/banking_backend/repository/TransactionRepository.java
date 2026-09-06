package com.abhay.banking_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhay.banking_backend.entity.Transaction;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {
}