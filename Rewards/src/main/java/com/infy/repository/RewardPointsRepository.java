package com.infy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.model.Customer;
import com.infy.model.RewardPoints;

@Repository
public interface RewardPointsRepository extends JpaRepository<RewardPoints, Long> {

	

	Object findByCustomerId(Long customerId);

	RewardPoints findByCustomerAndMonthAndYear(Customer customer, int month, int year);



	}