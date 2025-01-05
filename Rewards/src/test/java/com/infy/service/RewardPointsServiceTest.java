package com.infy.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.infy.model.Customer;
import com.infy.model.CustomerTransaction;
import com.infy.model.RewardPoints;
import com.infy.repository.RewardPointsRepository;

import java.time.LocalDate;

public class RewardPointsServiceTest {

    @Mock
    private RewardPointsRepository rewardPointsRepo;

    @Mock
    private CustomerTransaction transaction;

    @Mock
    private RewardPoints rewardPoints;

    @Mock
    private Customer customer;

    @InjectMocks
    private RewardPointsService rewardPointsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateMonthlyRewards_RewardPointsExists() {
        
        LocalDate transactionDate = LocalDate.of(2025, 1, 5);
        when(transaction.getDate()).thenReturn(transactionDate);
        when(transaction.getCustomer()).thenReturn(customer);
        when(transaction.getAmount()).thenReturn(100.0);

       
        when(rewardPointsRepo.findByCustomerAndMonthAndYear(customer, 1, 2025)).thenReturn(rewardPoints);
        when(rewardPoints.getPoints()).thenReturn(50);

        RewardPointsService rewardPointsServiceSpy = spy(rewardPointsService);
        doReturn(20).when(rewardPointsServiceSpy).calculatePoints(100.0); 

        rewardPointsServiceSpy.updateMonthlyRewards(transaction, rewardPointsRepo);

        verify(rewardPointsRepo).save(rewardPoints); 
        assertEquals(70, rewardPoints.getPoints()); 
    }
    
    @Test
    public void testUpdateMonthlyRewards_CalculatePointsReturnsNegative() {
       
        LocalDate transactionDate = LocalDate.of(2025, 1, 5);
        when(transaction.getDate()).thenReturn(transactionDate);
        when(transaction.getCustomer()).thenReturn(customer);
        when(transaction.getAmount()).thenReturn(100.0);

      
        when(rewardPointsRepo.findByCustomerAndMonthAndYear(customer, 1, 2025)).thenReturn(rewardPoints);
        when(rewardPoints.getPoints()).thenReturn(50);

        
        RewardPointsService rewardPointsServiceSpy = spy(rewardPointsService);
        doReturn(-5).when(rewardPointsServiceSpy).calculatePoints(100.0); 

        
        rewardPointsServiceSpy.updateMonthlyRewards(transaction, rewardPointsRepo);

        
        verify(rewardPointsRepo, never()).save(any()); 
    }

    @Test
    public void testUpdateMonthlyRewards_TransactionIsNull() {
       
        assertThrows(IllegalArgumentException.class, () -> {
            rewardPointsService.updateMonthlyRewards(null, rewardPointsRepo);
        });
    }
}

