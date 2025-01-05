package com.infy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.model.CustomerTransaction;
import com.infy.repository.CustomerTransactionRepository;
import com.infy.repository.RewardPointsRepository;
import com.infy.service.RewardPointsService;


@RestController
@RequestMapping("/api/transactions")
public class CustomerTransactionController {
	
	
	 @Autowired
	    private CustomerTransactionRepository transactionRepo;

	    @Autowired
	    private RewardPointsService rewardPointsService;

	    @Autowired
	    private RewardPointsRepository rewardPointsRepo;

	    @PostMapping
	    public ResponseEntity<CustomerTransaction> addTransaction(@RequestBody CustomerTransaction transaction) {
	        CustomerTransaction savedTransaction = transactionRepo.save(transaction);
	        rewardPointsService.updateMonthlyRewards(savedTransaction, rewardPointsRepo);
	        return ResponseEntity.ok(savedTransaction);
	    }

}
