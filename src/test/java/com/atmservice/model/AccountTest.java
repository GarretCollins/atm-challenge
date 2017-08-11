package com.atmservice.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest {

	@Test
	public void testGettersSetters() {
		Account acc = new Account();
		long id = 12345678910L;
		String accountNumber = "0000000000";
		double balance = 123.45;
		double odl = 100.00;
		String pin = "1234";
		acc.setId(id);
		acc.setAccountNumber(accountNumber);
		acc.setBalance(balance);
		acc.setOverDraftLimit(odl);
		acc.setPin(pin);
		assertTrue(acc.getAccountNumber().equalsIgnoreCase(accountNumber));
		assertTrue(acc.getBalance()==balance);
		assertTrue(acc.getOverDraftLimit()==odl);
		assertTrue(acc.getPin().equalsIgnoreCase(pin));
		assertTrue(acc.getId()==id);
	}

}
