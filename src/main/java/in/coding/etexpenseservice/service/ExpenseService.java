package in.coding.etexpenseservice.service;
import in.coding.etexpenseservice.data.model.expense.Expense;
import in.coding.etexpenseservice.data.model.expense.ExpenseData;

import java.util.List;

public interface ExpenseService {
     Expense createExpense(ExpenseData name, String userId);

     Expense getExpenseById(String expCatId, String userId);

     List<Expense> getExpenseListByUserId(String userId);

     Expense updateExpense(ExpenseData expense, String userId, String expenseId);

     void deleteExpenseById(String expenseId, String userId);
}
