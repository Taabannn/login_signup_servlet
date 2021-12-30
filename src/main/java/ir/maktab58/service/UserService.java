package ir.maktab58.service;

import ir.maktab58.dao.UserDao;
import ir.maktab58.entity.User;
import ir.maktab58.exceptions.RegisterException;
import ir.maktab58.service.validator.EmailValidator;
import ir.maktab58.service.validator.UserAndPassValidator;

/**
 * @author Taban Soleymani
 */
public class UserService {
    private final UserDao userDao = new UserDao();

    public int saveNewUser(String firstName, String lastName, String email, String username, String password) {
        UserAndPassValidator userAndPassValidator = UserAndPassValidator.getInstance();
        EmailValidator emailValidator = EmailValidator.getInstance();

        boolean userAndPassValid = userAndPassValidator.isUserAndPassValid(username, password);
        boolean emailValid = emailValidator.isEmailValid(email);

        if ((firstName == null) || (lastName == null))
            throw RegisterException.builder()
                    .withMessage("first name and last name can not be null.")
                    .withErrorCode(400).build();

        if (!emailValid)
            throw RegisterException.builder()
                    .withMessage("wrong format of email.")
                    .withErrorCode(400).build();

        if (!userAndPassValid)
            throw RegisterException.builder()
                    .withMessage("invalid user or pass.")
                    .withErrorCode(400).build();

        User user = User.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withUsername(username)
                .withPassword(password).build();

        if (userAndPassValid)
            return userDao.save(user);
        return 0;
    }
}
