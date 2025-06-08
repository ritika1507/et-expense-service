package in.coding.etexpenseservice.service;
import in.coding.etexpenseservice.data.model.expense.ExpenseCategory;

import java.util.List;

public interface ExpCatService {
     ExpenseCategory createExpCat(String name, String userId);

     ExpenseCategory getExpenseCatById(String expCatId, String userId);

     List<ExpenseCategory> getExpenseCatListByUserId(String userId);

     ExpenseCategory updateExpCat(String name, String userId, String expCatId);

     void deleteExpenseCatById(String expCatId, String userId);
}
