package com.atmservice.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.atmservice.model.ATM;
import com.atmservice.repository.ATMRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ATMServiceTest {

    private Long mockAtmId = 1l;
    private ATM mockAtm = new ATM(1l, 0d);

    @Mock
    private ATMRepository atmRepository;

    @InjectMocks
	private ATMServiceImpl atmService;

	@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(atmRepository.findOne(mockAtmId)).thenReturn(mockAtm);
    }

    @Test
    public void testAtmNotFound() {
        assertTrue(!atmService.hasSufficientFunds(100d, 2l));
    }

    @Test
    public void testAtmNotInsufficientFunds() {
    	 assertTrue(!atmService.hasSufficientFunds(100d, 1l));
    }

    @Test
    public void testSuccessfulAtmFundsValidation() {
	    boolean errorThrown = false;
	    try {
            when(atmRepository.findOne(1l)).thenReturn(new ATM(1l, 1000d));
            atmService.hasSufficientFunds(100d, 1l);
        } catch (Exception e) {
	        errorThrown = true;
        }
        assertThat(errorThrown, is(equalTo(false)));
    }

    @Test
    public void testAtmWithdrawalReducesFunds() {
        when(atmRepository.findOne(1l)).thenReturn(new ATM(1l, 1000d));
        double remainingFunds = atmService.withdrawal(200, 1l);
	    assertThat(remainingFunds, is(equalTo(800d)));
    }

    @Test
    public void testAtmDepositIncreasesFunds() {
        when(atmRepository.findOne(1l)).thenReturn(new ATM(1l, 1000d));
        double remainingFunds = atmService.depositAtmFunds(200, 1l);
        assertThat(remainingFunds, is(equalTo(1200d)));
    }
}
