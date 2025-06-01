package in.coding.etexpenseservice.service;

import in.coding.etexpenseservice.constants.ErrorMessaging;
import in.coding.etexpenseservice.constants.LoggingContants;
import in.coding.etexpenseservice.controller.dto.ExpCategory;
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
    public ExpCategory createExpCat(String name, String userId) {
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
        var expCat = ExpCategory.builder()
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
    public ExpCategory getExpenseById(String expCatId, String userId) {
        var methodName = "ExpCatServiceImpl : getExpenseById";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, expCatId, userId);

        var expcategory =  expCatRepo.findByExpCatIdAndUserId(expCatId, userId)
                .orElseThrow(() -> {
                    log.error(LoggingContants.ERROR_METHOD_LOG, methodName, expCatId);
                    return new ExpenseNotFound(
                            ErrorMessaging.EXPENSE_NOT_FOUND.getErrorMessage(),
                            ErrorMessaging.EXPENSE_NOT_FOUND.getErrorCode());
                        }
                );

        log.info(LoggingContants.END_METHOD_LOG ,methodName);
        return expcategory;
    }

    @Override
    public List<ExpCategory> getExpenseListByUserId(String userId) {
        var methodName = "ExpCatServiceImpl : getExpenseById";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, userId);

        List<ExpCategory> expcategory =  expCatRepo.findByUserId(userId);

        log.info(LoggingContants.END_METHOD_LOG ,methodName);
        return expcategory;
    }

    @Override
    public ExpCategory updateExpCat(String name, String userId, String expCatId) {
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
        var expCat = getExpenseById(expCatId, userId);

        expCat.setName(name.toLowerCase());

        //save it
        var savedExpCat = expCatRepo.save(expCat);

        log.info(LoggingContants.END_METHOD_LOG ,methodName);

        // return it
        return savedExpCat;
    }

    @Override
    public void deleteExpenseById(String expCatId, String userId) {
        var methodName = "ExpCatServiceImpl : deleteExpenseById";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, expCatId, userId);

        expCatRepo.deleteByExpCatIdAndUserId(expCatId, userId);

        log.info(LoggingContants.END_METHOD_LOG ,methodName);
    }
}
