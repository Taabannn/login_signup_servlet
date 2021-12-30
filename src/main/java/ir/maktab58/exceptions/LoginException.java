package ir.maktab58.exceptions;

import lombok.Builder;

/**
 * @author Taban Soleymani
 */
public class LoginException extends RuntimeException {
    private int errorCode;

    @Builder(setterPrefix = "with")
    public LoginException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
