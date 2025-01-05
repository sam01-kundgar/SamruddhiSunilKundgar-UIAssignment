package com.infy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.model.RewardPoints;
import com.infy.repository.RewardPointsRepository;



@RestController
@RequestMapping("/api/rewards")
public class RewardPointsController {
	
	
	 @Autowired
	    private RewardPointsRepository rewardPointsRepo;

	    @GetMapping("/{customerId}")
	    public ResponseEntity<Object> getCustomerRewards(@PathVariable Long customerId) {
	        return ResponseEntity.ok(rewardPointsRepo.findByCustomerId(customerId));
	    }

	    @GetMapping
	    public ResponseEntity<List<RewardPoints>> getAllRewards() {
	        return ResponseEntity.ok(rewardPointsRepo.findAll());
	    }

}
