package com.atmservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atmservice.service.AccountService;

@RestController

public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
    @RequestMapping(value = "/account/{accountNumber}/{pin}/getDetails", method = RequestMethod.GET)
    public Object getAccount(@PathVariable("accountNumber") String accountNumber, @PathVariable("pin") String pin) {
		return accountService.findAccountWith(accountNumber, pin);
	}

    @RequestMapping(value = "/account/{accountNumber}/{pin}/checkBalance", method = RequestMethod.GET)
    public String getAccountBalance(@PathVariable("accountNumber") String accountNumber, @PathVariable("pin") String pin) {
		return accountService.checkBalance(accountNumber, pin);
	}

    @RequestMapping(value = "/account/{accountNumber}/{pin}/withdraw/{amount}/{atmId}", method = RequestMethod.GET)
    public String getAccountBalanceAfterWithdraw(@PathVariable("accountNumber") String accountNumber, @PathVariable("pin") String pin, @PathVariable(value = "amount") double amount, @PathVariable("atmId") Long atmId) {
		return accountService.withdraw(accountNumber, pin, amount, atmId);
	}

}
