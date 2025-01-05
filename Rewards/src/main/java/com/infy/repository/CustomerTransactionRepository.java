package com.infy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.model.CustomerTransaction;


@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {}