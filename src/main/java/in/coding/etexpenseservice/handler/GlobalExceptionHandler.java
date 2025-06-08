package in.coding.etexpenseservice.handler;

import in.coding.etexpenseservice.controller.dto.ErrorResponse;
import in.coding.etexpenseservice.exception.ExpCatAlreadyExists;
import in.coding.etexpenseservice.exception.ExpenseNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpCatAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handleExpCatAlreadyExists(ExpCatAlreadyExists e){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ErrorResponse.builder()
                                .errorCode(e.getErrorCode())
                                .errorMessage(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(ExpenseNotFound.class)
    public ResponseEntity<ErrorResponse> handleExpenseNotFound(ExpenseNotFound e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.builder()
                                .errorCode(e.getErrorCode())
                                .errorMessage(e.getMessage())
                                .build()
                );
    }
}
