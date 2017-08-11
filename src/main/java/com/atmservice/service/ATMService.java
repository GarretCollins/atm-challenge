package com.atmservice.service;

public interface ATMService {
	boolean hasSufficientFunds(double amount, Long atmId);
    double withdrawal(double amount, Long atmId);
    double depositAtmFunds(double amount, Long atmId);
}
