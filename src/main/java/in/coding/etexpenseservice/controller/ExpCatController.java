package in.coding.etexpenseservice.controller;

import in.coding.etexpenseservice.constants.LoggingContants;
import in.coding.etexpenseservice.controller.dto.ExpCatRequest;
import in.coding.etexpenseservice.controller.dto.ExpCategory;
import in.coding.etexpenseservice.data.model.expense.ExpenseCategory;
import in.coding.etexpenseservice.service.ExpCatService;
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
public class ExpCatController {
    private final ExpCatService expCatService;

    @PostMapping("/")
    public ResponseEntity<ExpenseCategory> createExpCat(
            @PathVariable String userId,
            @RequestBody ExpCatRequest expCatRequest
    ){
        var methodName = "ExpCatController : createExpCat";
        log.info(LoggingContants.START_METHOD_LOG, methodName, userId, expCatRequest.getName());
        var expCategory = expCatService.createExpCat(expCatRequest.getName(), userId);
        log.info(LoggingContants.END_METHOD_LOG, methodName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expCategory);
    }

    @GetMapping("/{expCatId}")
    public ResponseEntity<ExpenseCategory> getExpenseById(
            @PathVariable String userId,
            @PathVariable String expCatId
    ){
        var methodName = "ExpCatController : getExpenseById";
        log.info(LoggingContants.START_METHOD_LOG, methodName, userId, expCatId);
        var expCategory = expCatService.getExpenseCatById(expCatId, userId);
        log.info(LoggingContants.END_METHOD_LOG, methodName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(expCategory);
    }

    @GetMapping("/")
    public ResponseEntity<List<ExpenseCategory>> getExpenseListByUserId(
            @PathVariable String userId
    ){
        var methodName = "ExpCatController : getExpenseById";
        log.info(LoggingContants.START_METHOD_LOG, methodName, userId);
        List<ExpenseCategory> expCategoryList = expCatService.getExpenseCatListByUserId(userId);
        log.info(LoggingContants.END_METHOD_LOG, methodName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(expCategoryList);
    }

    @PutMapping("/{expCatId}")
    public ResponseEntity<ExpenseCategory> createExpCat(
            @PathVariable String userId,
            @PathVariable String expCatId,
            @RequestBody ExpCatRequest expCatRequest
    ){
        var methodName = "ExpCatController : createExpCat";
        log.info(LoggingContants.START_METHOD_LOG, methodName, userId, expCatRequest.getName());
        var expCategory = expCatService.updateExpCat(expCatRequest.getName(), userId, expCatId);
        log.info(LoggingContants.END_METHOD_LOG, methodName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expCategory);
    }

    @DeleteMapping("/{expCatId}")
    public ResponseEntity<ExpCategory> deleteExpenseById(
            @PathVariable String userId,
            @PathVariable String expCatId
    ){
        var methodName = "ExpCatController : deleteExpenseById";
        log.info(LoggingContants.START_METHOD_LOG, methodName, userId, expCatId);
        expCatService.deleteExpenseCatById(expCatId, userId);
        log.info(LoggingContants.END_METHOD_LOG, methodName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
