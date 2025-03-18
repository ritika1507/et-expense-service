package in.coding.etexpenseservice.controller;

import in.coding.etexpenseservice.constants.LoggingContants;
import in.coding.etexpenseservice.controller.dto.ExpenseRequest;
import in.coding.etexpenseservice.data.model.expense.Expense;
import in.coding.etexpenseservice.data.model.mapper.ExpenseMapper;
import in.coding.etexpenseservice.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api/users/{userId}/categories")
@RequiredArgsConstructor
public class ExpController {
    private final ExpenseService expenseService;

    @PostMapping("/")
    public ResponseEntity<Expense> createExpense(
            @PathVariable String userId,
            @RequestBody ExpenseRequest expenseRequest
    ){
        var methodName = "ExpenseController : createExpense";
        log.info(LoggingContants.START_METHOD_LOG, methodName, userId, expenseRequest.getTitle());
        var expense = expenseService.createExpense(ExpenseMapper.INSTANCE.mapToExpense(expenseRequest), userId);
        log.info(LoggingContants.END_METHOD_LOG, methodName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expense);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<Expense> getExpenseenseById(
            @PathVariable String userId,
            @PathVariable String expenseId
    ){
        var methodName = "ExpenseController : getExpenseenseById";
        log.info(LoggingContants.START_METHOD_LOG, methodName, userId, expenseId);
        var expenseegory = expenseService.getExpenseenseById(expenseId, userId);
        log.info(LoggingContants.END_METHOD_LOG, methodName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(expenseegory);
    }

    @GetMapping("/")
    public ResponseEntity<List<Expense>> getExpenseenseListByUserId(
            @PathVariable String userId
    ){
        var methodName = "ExpenseController : getExpenseenseById";
        log.info(LoggingContants.START_METHOD_LOG, methodName, userId);
        List<Expense> expenseegoryList = expenseService.getExpenseenseListByUserId(userId);
        log.info(LoggingContants.END_METHOD_LOG, methodName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(expenseegoryList);
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<Expense> createExpense(
            @PathVariable String userId,
            @PathVariable String expenseId,
            @RequestBody ExpenseRequest expenseRequest
    ){
        var methodName = "ExpenseController : createExpense";
        log.info(LoggingContants.START_METHOD_LOG, methodName, userId, expenseRequest.getName());
        var expenseegory = expenseService.updateExpense(expenseRequest.getName(), userId, expenseId);
        log.info(LoggingContants.END_METHOD_LOG, methodName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expenseegory);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Expense> deleteExpenseenseById(
            @PathVariable String userId,
            @PathVariable String expenseId
    ){
        var methodName = "ExpenseController : deleteExpenseenseById";
        log.info(LoggingContants.START_METHOD_LOG, methodName, userId, expenseId);
        expenseService.deleteExpenseenseById(expenseId, userId);
        log.info(LoggingContants.END_METHOD_LOG, methodName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
