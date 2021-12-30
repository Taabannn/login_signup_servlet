package ir.maktab58.exceptions;

import lombok.Builder;

/**
 * @author Taban Soleymani
 */
public class RegisterException extends RuntimeException {
    private int errorCode;

    @Builder(setterPrefix = "with")
    public RegisterException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
