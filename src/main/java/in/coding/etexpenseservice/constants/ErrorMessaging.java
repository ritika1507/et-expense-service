package in.coding.etexpenseservice.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessaging {

    EXP_CAT_ALREADY_EXISTS("E409", "ExpenseRepo category already exists"),

    EXPENSE_NOT_FOUND("E404", "ExpenseRepo not found!");

    private final String errorCode;
    private final String errorMessage;
}
