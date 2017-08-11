package com.atmservice.service;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atmservice.commons.Constants;
import com.atmservice.model.Account;
import com.atmservice.repository.AccountRepository;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	private DecimalFormat df = new DecimalFormat("#.00");

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	private ATMService atmService;

	@Override
	public Object findAccountWith(String accountNumber, String pin) {
		Account account = findAccount(accountNumber, pin);
		if(account==null) { return Constants.ACCOUNT_ERROR; }
		return account;
	}

	private Account findAccount(String accountNumber, String pin) {
		return accountRepository.findByAccountNumberAndPin(accountNumber, pin);
	}

	@Override
	public String checkBalance(String accountNumber, String pin) {
		Account account = findAccount(accountNumber, pin);
		if(account==null) { return Constants.ACCOUNT_ERROR; }
		return String.valueOf(df.format(account.getBalance()));
	}

	@Override
	@Transactional
	public String withdraw(String accountNumber, String pin, double amount, Long atmId) {
		Account account = findAccount(accountNumber, pin);
		if(account==null) { return Constants.ACCOUNT_ERROR; }
		if(!hasAccountSufficentFunds(account.getBalance(), account.getOverDraftLimit(), amount)) {return Constants.FUNDS_ERROR;}
		if(!atmService.hasSufficientFunds(amount, atmId)) {return Constants.ATM_ERROR;}

		account.setBalance(account.getBalance() - amount);
		atmService.withdrawal(amount, atmId);

		return df.format(account.getBalance());
	}

	private boolean hasAccountSufficentFunds(double balance, double overdraft, double amount) {
		return ((balance + overdraft) >= amount);
	}
}