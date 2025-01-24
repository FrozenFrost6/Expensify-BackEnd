package com.anvesh.expensify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anvesh.expensify.model.Expense;
import com.anvesh.expensify.service.ExpenseService;
import com.anvesh.expensify.service.UserService;



@RestController
@RequestMapping("/expenses")
public class ExpenseController {
	
	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	UserService userService;
	
	
	
	
	@GetMapping
	public List<Expense> getUserExpenses(@AuthenticationPrincipal(expression = "username") String username) {
		return expenseService.findAllExpensesOfUser(username);
	}
	
	@PostMapping
	public Expense addUserExpense(@AuthenticationPrincipal(expression = "username") String username, @RequestBody Expense expense) {
		Expense createdExpense = expenseService.createExpenseOfUser(username, expense);
		return createdExpense;
	}
	
	@GetMapping("/{expenseId}")
	public Expense getUserExpenseById(@AuthenticationPrincipal(expression = "username") String username, @PathVariable Integer expenseId) {
		
		return expenseService.findUserExpenseById(username, expenseId);
	}
	
	@PutMapping("/{expenseId}")
	public Expense UpdateUserExpenseById(@AuthenticationPrincipal(expression = "username") String username, @PathVariable Integer expenseId, @RequestBody Expense updatedExpense) {
		
		return expenseService.updateUserExpenseById(username, expenseId, updatedExpense);
	}
	
	@DeleteMapping("/{expenseId}")
	public void deleteUserExpenseById(@AuthenticationPrincipal(expression = "username") String username, @PathVariable Integer expenseId) {
		expenseService.deleteUserExpenseById(username, expenseId);
	}
	
//	@GetMapping("/admin/expenses")
//	public List<Expense> getAllExpensesForAdminAccess() {
//		User loggedInUser = userService.getUserById(1);
//		return expenseService.findAllExpenses(loggedInUser);			
//	}
//	
	
//	@GetMapping("/expenses")
//	public List<Expense> getAllUserExpenses() {
//		User loggedInUser = userService.getUserById(1);
//		return expenseService.findAllExpensesOfUser(loggedInUser);			
//	}
//	
//	@PostMapping("/expenses")
//	public ResponseEntity<Expense> addUserExpense(@RequestBody Expense expense) {
//		// to do
//		User loggedInUser = userService.getUserById(1);
//		expense.setUser(loggedInUser);
//		
//		Expense createdExpense = expenseService.createExpenseOfUser(expense);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{userId}")
//				.build(createdExpense.getExpenseId());
//		
//		return ResponseEntity.created(location).build();
//	}
	
//	@GetMapping("/expenses/{expenseId}")
//	public Expense getUserExpense(@PathVariable Integer expenseId) {
//		Expense userExpense = expenseService.getExpenseOfUser(expenseId);
//		return userExpense;
//	}
//	
//	@PutMapping("/expenses/{expenseId}")
//	public Expense updateUserExpense(@PathVariable Integer expenseId, @RequestBody Expense expense) {
//		Expense updatedExpense = expenseService.updateExpenseOfUser(expenseId, expense);
//		return updatedExpense;
//	}
//	
//	@DeleteMapping("/expenses/{expenseId}")
//	public void deleteUserExpense(@PathVariable Integer expenseId) {
//		expenseService.deleteUserExpenseById(expenseId);
//	}
	
	// todo
	// save user data to h2  												done
	// save expense data to h2 												done
	// change from h2 to postgres using docker								done
	// create dummy application and learn authentication					done
	// create dummy react application and make calls from it after auth		done
	// transfer techniques to current application							in progress
	
}
