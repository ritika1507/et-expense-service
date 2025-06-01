package in.coding.etexpenseservice.service;


import in.coding.etexpenseservice.controller.dto.ExpCategory;

import java.util.List;

public interface ExpCatService {
     ExpCategory createExpCat(String name, String userId);

     ExpCategory getExpenseById(String expCatId, String userId);

     List<ExpCategory> getExpenseListByUserId(String userId);

     ExpCategory updateExpCat(String name, String userId, String expCatId);

     void deleteExpenseById(String expCatId, String userId);
}
