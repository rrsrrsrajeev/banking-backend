package com.abhay.banking_backend.repository;

import com.abhay.banking_backend.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository
        extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);
}