package com.anvesh.expensify.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anvesh.expensify.model.Expense;
import com.anvesh.expensify.model.User;

public interface ExpenseRepository extends JpaRepository<Expense, Integer>{

	
	List<Expense> findByUserUsername(String username);
	
    Optional<Expense> findByExpenseIdAndUser(Integer expenseId, User user);

}
