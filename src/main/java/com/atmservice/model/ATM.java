package com.atmservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ATM {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private double funds;
	
	public ATM() {
		super();
	}

	public ATM(Long id, double funds) {
		super();
		this.id = id;
		this.funds = funds;
	}
	
	public Long getId() {
	return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public double getFunds() {
		return funds;
	}

	public void setFunds(double funds) {
		this.funds = funds;
	}
}
