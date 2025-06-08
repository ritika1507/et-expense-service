package in.coding.etexpenseservice.service;

import in.coding.etexpenseservice.constants.ErrorMessaging;
import in.coding.etexpenseservice.constants.LoggingContants;
import in.coding.etexpenseservice.data.model.expense.ExpenseCategory;
import in.coding.etexpenseservice.data.repo.ExpCatRepo;
import in.coding.etexpenseservice.exception.ExpCatAlreadyExists;
import in.coding.etexpenseservice.exception.ExpenseNotFound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ExpCatServiceImpl implements ExpCatService{

    private final ExpCatRepo expCatRepo;

    @Override
    public ExpenseCategory createExpCat(String name, String userId) {
       //log
        var methodName = "ExpCatServiceImpl : createExpCat";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, name, userId);


        // check if category already exists for the user
        // if yes, throw exception
        if(expCatRepo.existsByNameAndUserId(name.toLowerCase(), userId)){
            log.error(LoggingContants.ERROR_METHOD_LOG, methodName, name + "already exists");
            throw new ExpCatAlreadyExists(
                    ErrorMessaging.EXP_CAT_ALREADY_EXISTS.getErrorMessage(),
                    ErrorMessaging.EXP_CAT_ALREADY_EXISTS.getErrorCode()
            );
        }

        // if not, create it
        var expCat = ExpenseCategory.builder()
                .userId(userId)
                .name(name.toLowerCase())
                .build();

        //save it
        var savedExpCat = expCatRepo.save(expCat);

        log.info(LoggingContants.END_METHOD_LOG ,methodName);

        // return it
        return savedExpCat;
    }

    @Override
    public ExpenseCategory getExpenseCatById(String expCatId, String userId) {
        var methodName = "ExpCatServiceImpl : getExpenseById";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, expCatId, userId);

        var expCategory =  expCatRepo.findByExpCatIdAndUserId(expCatId, userId)
                .orElseThrow(() -> {
                    log.error(LoggingContants.ERROR_METHOD_LOG, methodName, expCatId);
                    return new ExpenseNotFound(
                            ErrorMessaging.EXPENSE_NOT_FOUND.getErrorMessage(),
                            ErrorMessaging.EXPENSE_NOT_FOUND.getErrorCode());
                        }
                );

        log.info(LoggingContants.END_METHOD_LOG ,methodName);
        return expCategory;
    }

    @Override
    public List<ExpenseCategory> getExpenseCatListByUserId(String userId) {
        var methodName = "ExpCatServiceImpl : getExpenseById";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, userId);

        List<ExpenseCategory> expcategory =  expCatRepo.findByUserId(userId);

        log.info(LoggingContants.END_METHOD_LOG ,methodName);
        return expcategory;
    }

    @Override
    public ExpenseCategory updateExpCat(String name, String userId, String expCatId) {
        var methodName = "ExpCatServiceImpl : updateExpCat";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, name, userId);


        // check if category already exists for the user
        // if yes, throw exception
        if(expCatRepo.existsByNameAndUserId(name.toLowerCase(), userId)){
            log.error(LoggingContants.ERROR_METHOD_LOG, methodName, name + "already exists");
            throw new ExpCatAlreadyExists(
                    ErrorMessaging.EXP_CAT_ALREADY_EXISTS.getErrorMessage(),
                    ErrorMessaging.EXP_CAT_ALREADY_EXISTS.getErrorCode()
            );
        }

        // if not,find and update
        var expCat = getExpenseCatById(expCatId, userId);

        expCat.setName(name.toLowerCase());

        //save it
        var savedExpCat = expCatRepo.save(expCat);

        log.info(LoggingContants.END_METHOD_LOG ,methodName);

        // return it
        return savedExpCat;
    }

    @Override
    public void deleteExpenseCatById(String expCatId, String userId) {
        var methodName = "ExpCatServiceImpl : deleteExpenseById";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, expCatId, userId);

        expCatRepo.deleteByExpCatIdAndUserId(expCatId, userId);

        log.info(LoggingContants.END_METHOD_LOG ,methodName);
    }
}
