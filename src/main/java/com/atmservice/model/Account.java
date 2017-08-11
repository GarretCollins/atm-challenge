package com.atmservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String accountNumber;
	private double balance;
	private double overDraftLimit;
	private String pin;
	
	public Account() {
		super();
	}

	public Account(Long id, String accountNumber, double balance, double overDraftLimit, String pin) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.overDraftLimit = overDraftLimit;
		this.pin = pin;
	}
	
	public Long getId() {
	return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getOverDraftLimit() {
		return overDraftLimit;
	}

	public void setOverDraftLimit(double overDraftLimit) {
		this.overDraftLimit = overDraftLimit;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
}
