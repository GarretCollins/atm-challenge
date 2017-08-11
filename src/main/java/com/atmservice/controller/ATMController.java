package com.atmservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atmservice.service.ATMService;

@RestController
public class ATMController {

	@Autowired
	private ATMService atmService;

    @RequestMapping(value = "/atm/depositAtmFunds/{atmDeposit}/{atmId}", method = RequestMethod.GET)
    public double setAtmFunds(@PathVariable("atmDeposit") double atmDeposit, @PathVariable("atmId") Long atmId) {
		return atmService.depositAtmFunds(atmDeposit, atmId);
	}
}
