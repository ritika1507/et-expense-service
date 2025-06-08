package in.coding.etexpenseservice.data.repo;

import in.coding.etexpenseservice.data.model.expense.ExpenseCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ExpCatRepo extends MongoRepository<ExpenseCategory, String> {

    boolean existsByNameAndUserId(String lowerCase, String userId);

    void deleteByExpCatIdAndUserId(String expCatId, String userId);

    Optional<ExpenseCategory> findByExpCatIdAndUserId(String expCatId, String userId);

    List<ExpenseCategory> findByUserId(String userId);
}
