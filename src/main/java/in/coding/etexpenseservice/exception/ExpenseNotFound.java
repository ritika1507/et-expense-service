package in.coding.etexpenseservice.exception;

import lombok.Getter;

@Getter
public class ExpenseNotFound extends RuntimeException{

    private final String errorCode;;

    public ExpenseNotFound(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
