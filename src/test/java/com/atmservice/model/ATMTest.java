package com.atmservice.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ATMTest {

	@Test
	public void testGettersSetters() {
		ATM atm = new ATM();
		double funds = 2000.00;
		long id = 1l;
		
		atm.setId(id);
		atm.setFunds(funds);
		
		assertTrue(atm.getFunds()==funds);
		assertTrue(atm.getId()==id);
	}

}
