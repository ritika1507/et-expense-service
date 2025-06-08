package in.coding.etexpenseservice.service;

import in.coding.etexpenseservice.constants.ErrorMessaging;
import in.coding.etexpenseservice.constants.LoggingContants;
import in.coding.etexpenseservice.data.model.expense.Expense;
import in.coding.etexpenseservice.data.model.expense.ExpenseData;
import in.coding.etexpenseservice.data.repo.ExpenseRepo;
import in.coding.etexpenseservice.exception.ExpenseNotFound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{


    private final ExpenseRepo expenseRepo;

    private final ExpCatService expCatService;


    @Override
    public Expense createExpense(ExpenseData request, String userId) {
       //log
        var methodName = "ExpServiceImpl : createExpense";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, request, userId);

        // find expense category by id
        var expCat = expCatService.getExpenseCatById(request.getExpCatId(), userId);

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
        return  savedExpCat;
    }

    @Override
    public Expense getExpenseById(String expId, String userId) {
        var methodName = "ExpCatServiceImpl : getExpenseById";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, expId, userId);

        var expense =  expenseRepo.findByExpIdAndUserId(expId, userId)
                .orElseThrow(() -> {
                    log.error(LoggingContants.ERROR_METHOD_LOG, methodName, expId);
                    return new ExpenseNotFound(
                            ErrorMessaging.EXPENSE_NOT_FOUND.getErrorMessage(),
                            ErrorMessaging.EXPENSE_NOT_FOUND.getErrorCode());
                        }
                );

        log.info(LoggingContants.END_METHOD_LOG ,methodName);
        return expense;
    }

    @Override
    public List<Expense> getExpenseListByUserId(String userId) {
        var methodName = "ExpCatServiceImpl : getExpenseById";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, userId);

        List<Expense> expenseList =  expenseRepo.findByUserId(userId);

        log.info(LoggingContants.END_METHOD_LOG ,methodName);
        return expenseList;
    }

    @Override
    public Expense updateExpense(ExpenseData expenseData, String userId, String expId) {
        var methodName = "ExpCatServiceImpl : updateExpCat";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, expenseData, userId);


        //find expense by id
        var expense = getExpenseById(expId, userId);

        // find exp cat by id
        var expenseCategory = expCatService.getExpenseCatById(userId, expId);

        //update expense
        expense.setTitle(expenseData.getTitle());
        expense.setAmount(expense.getAmount());
        expense.setExpCat(expenseCategory);

        //save it
        var savedExp = expenseRepo.save(expense);

        log.info(LoggingContants.END_METHOD_LOG ,methodName);

        // return it
        return savedExp;
    }

    @Override
    public void deleteExpenseById(String expenseId, String userId) {
        var methodName = "ExpCatServiceImpl : deleteExpenseById";
        log.info(LoggingContants.START_METHOD_LOG ,methodName, expenseId, userId);

        expenseRepo.deleteExpenseByExpIdAndUserId(expenseId, userId);

        log.info(LoggingContants.END_METHOD_LOG ,methodName);
    }
}
