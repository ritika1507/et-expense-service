package in.coding.etexpenseservice.data.repo;

import in.coding.etexpenseservice.data.model.expense.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepo extends MongoRepository<Expense, String> {

    Optional<Expense> findByExpIdAndUserId(String expId, String userId);

    List<Expense> findByUserId(String userId);

    void deleteExpenseByExpIdAndUserId(String expId, String userId);
}
