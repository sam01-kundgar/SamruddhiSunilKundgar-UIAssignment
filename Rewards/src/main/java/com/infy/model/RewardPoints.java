package com.infy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class RewardPoints {
    public RewardPoints(Long id, Customer customer, int month, int year, int points) {
		super();
		this.id = id;
		this.customer = customer;
		this.month = month;
		this.year =year;
		this.points = points;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;
  
    private int points;
    
    @Column(name = "month") 
    private int month; 

    @Column(name = "year")   
    private int year;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getmonth() {
		return month;
	}
	public void setmonth(int month) {
		this.month = month;
	}
	public int getyear() {
		return year;
	}
	public void setyear(int year) {
		this.year = year;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}

   
}