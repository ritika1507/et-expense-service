package in.coding.etexpenseservice.service;

import in.coding.etexpenseservice.constants.ErrorMessaging;
import in.coding.etexpenseservice.constants.LoggingContants;
import in.coding.etexpenseservice.data.model.expCat.ExpCategory;
import in.coding.etexpenseservice.data.model.expense.Expense;
import in.coding.etexpenseservice.data.model.expense.ExpenseData;
import in.coding.etexpenseservice.data.repo.ExpCatRepo;
import in.coding.etexpenseservice.data.repo.ExpenseRepo;
import in.coding.etexpenseservice.exception.ExpCatAlreadyExists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpCatService expCatService;

    private final ExpenseRepo expenseRepo;

    @Override
    public Expense createExpense(ExpenseData request, String userId) {
       //log
        var methodName = "ExpServiceImpl : createExpense";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, request, userId);

        // find expense category by id
        var expCat = expCatService.getExpenseById(request.getExpCatId(), userId);

        //create expense
        var expense = Expense.builder()
                .userId(userId)
                .expCat(expCat)
                .title(request.getTitle())
                .amount(request.getAmount())
                .build();


        //save it
        var savedExpCat = expenseRepo.save(expense);

        log.info(LoggingContants.END_METHOD_LOG ,methodName);

        // return it
        return savedExpCat;
    }

//    @Override
//    public ExpCategory getExpenseById(String expCatId, String userId) {
//        var methodName = "ExpCatServiceImpl : getExpenseById";
//        log.info(LoggingContants.START_METHOD_LOG ,methodName, expCatId, userId);
//
//        var expcategory =  expCatRepo.findByExpCatIdAndUserId(expCatId, userId)
//                .orElseThrow(() -> {
//                    log.error(LoggingContants.ERROR_METHOD_LOG, methodName, expCatId);
//                    return new ExpenseNotFound(
//                            ErrorMessaging.EXPENSE_NOT_FOUND.getErrorMessage(),
//                            ErrorMessaging.EXPENSE_NOT_FOUND.getErrorCode());
//                        }
//                );
//
//        log.info(LoggingContants.END_METHOD_LOG ,methodName);
//        return expcategory;
//    }
//
//    @Override
//    public List<ExpCategory> getExpenseListByUserId(String userId) {
//        var methodName = "ExpCatServiceImpl : getExpenseById";
//        log.info(LoggingContants.START_METHOD_LOG ,methodName, userId);
//
//        List<ExpCategory> expcategory =  expCatRepo.findByUserId(userId);
//
//        log.info(LoggingContants.END_METHOD_LOG ,methodName);
//        return expcategory;
//    }
//
//    @Override
//    public ExpCategory updateExpCat(String name, String userId, String expCatId) {
//        var methodName = "ExpCatServiceImpl : updateExpCat";
//        log.info(LoggingContants.START_METHOD_LOG ,methodName, name, userId);
//
//
//        // check if category already exists for the user
//        // if yes, throw exception
//        if(expCatRepo.existsByNameAndUserId(name.toLowerCase(), userId)){
//            log.error(LoggingContants.ERROR_METHOD_LOG, methodName, name + "already exists");
//            throw new ExpCatAlreadyExists(
//                    ErrorMessaging.EXP_CAT_ALREADY_EXISTS.getErrorMessage(),
//                    ErrorMessaging.EXP_CAT_ALREADY_EXISTS.getErrorCode()
//            );
//        }
//
//        // if not,find and update
//        var expCat = expCatRepo.findByExpCatIdAndUserId(expCatId, userId)
//                .orElseThrow(() -> {
//                    log.error(LoggingContants.ERROR_METHOD_LOG, methodName, expCatId);
//                    return new ExpenseNotFound(
//                            ErrorMessaging.EXPENSE_NOT_FOUND.getErrorMessage(),
//                            ErrorMessaging.EXPENSE_NOT_FOUND.getErrorCode());
//                });
//
//        expCat.setName(name);
//
//        //save it
//        var savedExpCat = expCatRepo.save(expCat);
//
//        log.info(LoggingContants.END_METHOD_LOG ,methodName);
//
//        // return it
//        return savedExpCat;
//    }
//
//    @Override
//    public void deleteExpenseById(String expCatId, String userId) {
//        var methodName = "ExpCatServiceImpl : deleteExpenseById";
//        log.info(LoggingContants.START_METHOD_LOG ,methodName, expCatId, userId);
//
//        expCatRepo.deleteByExpCatIdAndUserId(expCatId, userId);
//
//        log.info(LoggingContants.END_METHOD_LOG ,methodName);
//    }
}
