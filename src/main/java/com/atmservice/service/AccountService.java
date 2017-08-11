package com.atmservice.service;

public interface AccountService {
	String checkBalance(String accountNumber, String pin);
	String withdraw(String accountNumber, String pin, double amount, Long atmId);
    Object findAccountWith(String accountNumber, String pin);
}