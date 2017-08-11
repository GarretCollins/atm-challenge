package com.atmservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.atmservice.model.ATM;
import com.atmservice.model.Account;
import com.atmservice.repository.ATMRepository;
import com.atmservice.repository.AccountRepository;

@SpringBootApplication
public class TellerApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(TellerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TellerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner setup(AccountRepository accountRepository, ATMRepository atmRepository) {
		return args -> {
			accountRepository.save(new Account(1l, "12345678", 500, 100, "1234"));
			accountRepository.save(new Account(2l, "87654321", 100, 0, "4321"));
			accountRepository.save(new Account(3l, "56784321", 0, 0, "1245"));
			atmRepository.save(new ATM(1L, 8000));
			
			logger.info("Generated Sample Data For H2");
		};
	}
}
