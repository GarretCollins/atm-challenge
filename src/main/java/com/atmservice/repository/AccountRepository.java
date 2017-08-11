package com.atmservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atmservice.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByAccountNumberAndPin(String accountNumber, String pin);
}

