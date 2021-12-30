package ir.maktab58.exceptions;

import lombok.Builder;

/**
 * @author Taban Soleymani
 */
public class SignupException extends RuntimeException {
    private int errorCode;

    @Builder(setterPrefix = "with")
    public SignupException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
