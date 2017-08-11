package com.atmservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atmservice.model.ATM;

@Repository("atmRepository")
public interface ATMRepository extends JpaRepository<ATM, Long> {
}
