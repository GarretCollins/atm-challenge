package com.atmservice.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.atmservice.commons.Constants;
import com.atmservice.model.Account;
import com.atmservice.repository.ATMRepository;
import com.atmservice.repository.AccountRepository;

public class AccountServiceImplTest {
	
	private Long mockAtmId = 1l;
    private String mockAccountNum = "12345678";
    private String mockPin = "1234";
    private Account mockAccount = new Account(1l, mockAccountNum, 500, 100, mockPin);
    private List<Account> mockDB = Arrays.asList(mockAccount);

    @Mock
    private ATMService atmService;

    @Mock
    private AccountRepository accountRepository;
    
    @Mock
    private ATMRepository atmRepository;

    @InjectMocks
	private AccountServiceImpl accountService;

	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(accountRepository.findAll()).thenReturn(mockDB);
    }
	
	@Test
	public void testCheckBalance() {
        when(accountRepository.findByAccountNumberAndPin(Mockito.anyString(), Mockito.anyString())).thenReturn(mockAccount);
		String result = accountService.checkBalance(mockAccountNum, mockPin);
		assertThat(Double.valueOf(result), is(equalTo(500d)));
	}

    @Test
	public void testCheckBalanceFailureAccountNotFound() {
        String invalidAccountNum = "456464666";
	    assertTrue(accountService.checkBalance(invalidAccountNum, mockPin) == Constants.ACCOUNT_ERROR);
	}

    @Test
    public void testSuccessfulWithdrawal() {
        when(accountRepository.findByAccountNumberAndPin(Mockito.anyString(), Mockito.anyString())).thenReturn(mockAccount);
        when(atmService.hasSufficientFunds(100, mockAtmId)).thenReturn(true);
        atmService.depositAtmFunds(8000d, mockAtmId);
        String result = accountService.withdraw(mockAccountNum, mockPin, 100, mockAtmId);
        assertThat(Double.valueOf(result), is(equalTo(400d)));
    }

	@Test
	public void testWithdrawFailure() {
        when(accountRepository.findByAccountNumberAndPin(Mockito.anyString(), Mockito.anyString())).thenReturn(mockAccount);
        atmService.depositAtmFunds(8000d, mockAtmId);
        assertTrue(accountService.withdraw(mockAccountNum, mockPin, 700, mockAtmId)==Constants.FUNDS_ERROR);
	}

	@Test
	public void testAccountByAccountNumber() {
        when(accountRepository.findByAccountNumberAndPin(Mockito.anyString(), Mockito.anyString())).thenReturn(mockAccount);
		Account result = (Account)accountService.findAccountWith(mockAccountNum, mockPin);
        assertThat(result.getAccountNumber(), is(equalTo(mockAccountNum)));
	}

	@Test
	public void testAccountByAccountPinNumberFailure() {
	    String invalidPin = "5555";
        assertTrue(accountService.findAccountWith(mockAccountNum, invalidPin) == Constants.ACCOUNT_ERROR);
	}
}
