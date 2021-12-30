package ir.maktab58.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Taban Soleymani
 */
public class EmailValidator {
    private static final EmailValidator emailValidator = new EmailValidator();
    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
            + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private EmailValidator() {
    }

    public static EmailValidator getInstance() {
        return emailValidator;
    }

    public boolean isEmailValid(String email) {
        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

        if (email == null) {
            return false;
        }
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }
}
