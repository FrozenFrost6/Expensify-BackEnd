package com.anvesh.expensify.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer expenseId;
	
	private String expenseType;
	private String description;
	private Double amount;
	private String owedTo;
	private LocalDateTime createdAt;
	
	@ManyToOne
	private User user;

	public Expense(String expensetype, String description, Double amount, String owedTo,
			LocalDateTime createdAt) {
		super();
		this.expenseType = expensetype;
		this.description = description;
		this.amount = amount;
		this.owedTo = owedTo;
		this.createdAt = createdAt;
	}

	public Expense(String expensetype, String description, Double amount, String owedTo,
			LocalDateTime createdAt, User user) {
		super();
		this.expenseType = expensetype;
		this.description = description;
		this.amount = amount;
		this.owedTo = owedTo;
		this.createdAt = createdAt;
		this.user = user;
	}

	public Expense() {

	}

	public Integer getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Integer expenseId) {
		this.expenseId = expenseId;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expensetype) {
		this.expenseType = expensetype;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getOwedTo() {
		return owedTo;
	}

	public void setOwedTo(String owedTo) {
		this.owedTo = owedTo;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
