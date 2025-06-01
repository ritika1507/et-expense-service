package in.coding.etexpenseservice.exception;

public class ExpenseNotFound extends RuntimeException{

    private String errorCode;;

    public ExpenseNotFound(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
