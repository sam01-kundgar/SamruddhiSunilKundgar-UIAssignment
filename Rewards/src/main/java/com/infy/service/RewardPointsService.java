package com.infy.service;

import org.springframework.stereotype.Service;

import com.infy.model.Customer;
import com.infy.model.CustomerTransaction;
import com.infy.model.RewardPoints;
import com.infy.repository.RewardPointsRepository;

@Service
public class RewardPointsService {

	   

		public int calculatePoints(double amount) {
	        int points = 0;
	        if (amount > 100) {
	            points += (amount - 100) * 2;
	            points += 50; // $50 to $100 => 1 point per dollar
	        } else if (amount > 50) {
	            points += (amount - 50);
	        }
	        return points;
	    }

	    public void updateMonthlyRewards(CustomerTransaction transaction, RewardPointsRepository rewardPointsRepo) {
	        try {
	            // Validate the input transaction and repository
	            if (transaction == null || rewardPointsRepo == null) {
	                throw new IllegalArgumentException("Transaction or RewardPointsRepository cannot be null");
	            }

	            int month = transaction.getDate().getMonthValue();
	            int year = transaction.getDate().getYear();
	            Customer customer = transaction.getCustomer();

	            if (customer == null) {
	                throw new IllegalArgumentException("Customer information is missing");
	            }

	            RewardPoints rewardPoints = rewardPointsRepo.findByCustomerAndMonthAndYear(customer, month, year);

	            if (rewardPoints == null) {
	                return;  // No reward points record for the customer in that month, so nothing to update
	            }

	            int transactionPoints = calculatePoints(transaction.getAmount());

	            if (transactionPoints < 0) {
	                throw new IllegalArgumentException("Calculated reward points cannot be negative");
	            }

	            rewardPoints.setPoints(rewardPoints.getPoints() + transactionPoints);

	            rewardPointsRepo.save(rewardPoints);

	        } catch (NullPointerException e) {
	            
	            System.err.println("Null pointer exception: " + e.getMessage());
	            
	        } catch (IllegalArgumentException e) {
	            
	            System.err.println("Illegal argument exception: " + e.getMessage());
	            
	        } catch (Exception e) {
	            
	            System.err.println("Unexpected exception: " + e.getMessage());
	            e.printStackTrace();
	            
	        }
	    }
}


