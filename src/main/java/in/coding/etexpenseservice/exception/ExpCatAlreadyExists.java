package in.coding.etexpenseservice.exception;


import lombok.Getter;

@Getter
public class  ExpCatAlreadyExists extends RuntimeException {
    private final String errorCode;

    public ExpCatAlreadyExists(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
