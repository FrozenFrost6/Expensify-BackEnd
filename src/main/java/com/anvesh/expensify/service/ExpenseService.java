package com.anvesh.expensify.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anvesh.expensify.exceptions.ExpenseNotFoundException;
import com.anvesh.expensify.exceptions.UnauthorizedAccessException;
import com.anvesh.expensify.model.Expense;
import com.anvesh.expensify.model.User;
import com.anvesh.expensify.repository.ExpenseRepository;

@Service
public class ExpenseService {

	@Autowired
	private UserService userService;

	@Autowired
	private ExpenseRepository expenseRepository;
	

	public List<Expense> findAllExpensesOfUser(String username) {
		return expenseRepository.findByUserUsername(username);
	}
	
	
	 public Expense createExpenseOfUser(String username, Expense expense) {
	        User user = userService.getUserByUsername(username);

	        expense.setUser(user);
	        expense.setExpenseId(null);
	        
	        if (expense.getCreatedAt() == null) {
	            expense.setCreatedAt(LocalDateTime.now());
	        }

	        return expenseRepository.save(expense);
	    }
	 
	 public Expense findUserExpenseById(String username, Integer expenseId) {
        User user = userService.getUserByUsername(username);

        Optional<Expense> expense = expenseRepository.findByExpenseIdAndUser(expenseId, user);

        if (expense.isPresent()) {
            return expense.get();
        } else {
            throw new ExpenseNotFoundException("Expense not found for user " + username + " with ID: " + expenseId);
        }
	 }
	 
	 public Expense updateUserExpenseById(String username, Integer expenseId, Expense newExpense) {
		 User user = userService.getUserByUsername(username);
		 Optional<Expense> expense = expenseRepository.findByExpenseIdAndUser(expenseId, user);
		 
		 if (expense.isPresent()) {
	        Expense expenseToUpdate = expense.get();
	        
	        if (newExpense.getExpenseType() != null) {
			expenseToUpdate.setExpenseType(newExpense.getExpenseType());
			}
			if (newExpense.getDescription() != null) {
				expenseToUpdate.setDescription(newExpense.getDescription());
			}
			if (newExpense.getAmount() != null) {
				expenseToUpdate.setAmount(newExpense.getAmount());
			}
			if (newExpense.getOwedTo() != null) {
				expenseToUpdate.setOwedTo(newExpense.getOwedTo());
			}
			if (newExpense.getCreatedAt() != null) {
				expenseToUpdate.setCreatedAt(newExpense.getCreatedAt());
			}
	        
			return expenseRepository.save(expenseToUpdate);
	    } else {
	        throw new ExpenseNotFoundException("Expense not found for user " + username + " with ID: " + expenseId);
	    }
	 }
	 
	 public void deleteUserExpenseById(String username, Integer expenseId) {
		 Expense expenseToDelete = findUserExpenseById(username, expenseId);
		 expenseRepository.deleteById(expenseToDelete.getExpenseId());
	 }
	 
//	 public Expense createExpenseOfUser(Expense expense) {
//			expense.setExpenseId(null);
//			return expenseRepository.save(expense);
//		}
//		public List<Expense> findAllExpenses(User user) {
//		if (!user.getUserRole().equals(User.ExpensifyUserRole.ADMIN)) {
//			throw new UnauthorizedAccessException("Only admin users can access the information of all users.");
//		}
//		return expenseRepository.findAll();
//	}

//	public Expense updateExpenseOfUser(Integer expenseId, Expense newExpense) {
//		Expense expenseToUpdate = getExpenseOfUser(expenseId);
//		
//		if (newExpense.getExpensetype() != null) {
//			expenseToUpdate.setExpensetype(newExpense.getExpensetype());
//		}
//		if (newExpense.getDescription() != null) {
//			expenseToUpdate.setDescription(newExpense.getDescription());
//		}
//		if (newExpense.getAmount() != null) {
//			expenseToUpdate.setAmount(newExpense.getAmount());
//		}
//		if (newExpense.getOwedTo() != null) {
//			expenseToUpdate.setOwedTo(newExpense.getOwedTo());
//		}
//		if (newExpense.getCreatedAt() != null) {
//			expenseToUpdate.setCreatedAt(newExpense.getCreatedAt());
//		}
//				
//		return expenseRepository.save(expenseToUpdate);
//	}
//
//
//	public void deleteUserExpenseById(Integer expenseId) {
////		expenses.removeIf(currentExpense -> currentExpense.getExpenseId().equals(expenseId));
//		expenseRepository.deleteById(expenseId);
//	}

	
//	private Expense findExpenseOfUserById(Integer expenseId) {
//	return expenses.stream().filter(currentExpense -> currentExpense.getExpenseId().equals(expenseId)).findFirst()
//			.orElseThrow(() -> new ExpenseNotFoundException("Expense with ID " + expenseId + " not found"));
//}
}



//private static ArrayList<Expense> expenses = new ArrayList<>();
//
//
//static {
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//	expenses.add(
//			new Expense(
//				"Food", 
//				"Foodaholic Egg biriyani", 
//				279.87, 
//				"none",
//				LocalDateTime.parse("26-09-2024 14:30", formatter), 
//				new User("anveshch6", "pass", "Anvesh", "Ch", true)));
//	
//	expenses.add(
//			new Expense(
//					"Food", 
//					"Foodaholic Shanghai paneer", 
//					325.00, 
//					"none",
//					LocalDateTime.parse("26-08-2024 17:30", formatter), 
//					new User("zashz", "pass", "aneesh", "Ch", false)));
//}
//
