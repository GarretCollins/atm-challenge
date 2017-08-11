package com.atmservice.service;


import com.atmservice.model.ATM;
import com.atmservice.repository.ATMRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ATMServiceImpl implements ATMService {

    @Autowired
    private ATMRepository atmRepository;

    public boolean hasSufficientFunds(double amount, Long atmId) {
        ATM atm = atmRepository.findOne(atmId);
        if (atm == null) { return false; }
        return (atm.getFunds() >= amount);
    }

    // Not @transactional as this is downstream from accountService withdrawal
    public double withdrawal(double amount, Long atmId) {
        ATM atm = atmRepository.findOne(atmId);
        double remainingFunds = atm.getFunds() - amount;
        atm.setFunds(remainingFunds);
        return remainingFunds;
    }

    @Transactional
    public double depositAtmFunds(double amount, Long atmId) {
        ATM atm = atmRepository.findOne(atmId);
        atm.setFunds(atm.getFunds() + amount);
        return atm.getFunds();
    }
}
