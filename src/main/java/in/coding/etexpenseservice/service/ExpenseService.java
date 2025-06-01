package in.coding.etexpenseservice.service;

import in.coding.etexpenseservice.controller.dto.ExpenseRequest;
import in.coding.etexpenseservice.data.model.expense.Expense;
import in.coding.etexpenseservice.data.model.expense.ExpenseData;

public interface ExpenseService {
     Expense createExpense(ExpenseData name, String userId);
}
