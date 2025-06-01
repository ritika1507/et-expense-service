package in.coding.etexpenseservice.data.repo;

import in.coding.etexpenseservice.controller.dto.ExpCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ExpCatRepo extends MongoRepository<ExpCategory, String> {

    boolean existsByNameAndUserId(String lowerCase, String userId);

    void deleteByExpCatIdAndUserId(String expCatId, String userId);

    Optional<ExpCategory> findByExpCatIdAndUserId(String expCatId, String userId);

    List<ExpCategory> findByUserId(String userId);
}
