package ir.maktab58.service;

import ir.maktab58.dao.UserDao;
import ir.maktab58.entity.User;
import ir.maktab58.exceptions.SignupException;
import ir.maktab58.service.validator.EmailValidator;
import ir.maktab58.service.validator.UserAndPassValidator;

/**
 * @author Taban Soleymani
 */
public class UserService {
    private final UserDao userDao = new UserDao();

    public int saveNewUser(String firstName, String lastName, String email, String username, String password) {
        validateEnterdFieldsForUser(firstName, lastName, email, username, password);

        User user = User.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withUsername(username)
                .withPassword(password)
                .withEmail(email).build();

        return userDao.save(user);
    }

    private void validateEnterdFieldsForUser(String firstName, String lastName, String email, String username, String password) {
        UserAndPassValidator userAndPassValidator = UserAndPassValidator.getInstance();
        EmailValidator emailValidator = EmailValidator.getInstance();

        boolean userAndPassValid = userAndPassValidator.isUserAndPassValid(username, password);
        boolean emailValid = emailValidator.isEmailValid(email);

        if ((firstName == null) || (lastName == null))
            throw SignupException.builder()
                    .withMessage("first name and last name can not be null.")
                    .withErrorCode(400).build();

        if (!emailValid)
            throw SignupException.builder()
                    .withMessage("wrong format of email.")
                    .withErrorCode(400).build();

        if (!userAndPassValid)
            throw SignupException.builder()
                    .withMessage("invalid user or pass.")
                    .withErrorCode(400).build();
    }

    public User getUserByUserAndPass(String username, String password) {
        return userDao.findUserByUserAndPass(username, password);
    }
}
