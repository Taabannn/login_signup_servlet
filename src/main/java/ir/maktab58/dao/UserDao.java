package ir.maktab58.dao;

import ir.maktab58.entity.User;
import ir.maktab58.exceptions.LoginException;
import ir.maktab58.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

/**
 * @author Taban Soleymani
 */
public class UserDao extends BaseDaoInterfaceImpl<User> {
    public User findUserByUserAndPass(String username, String password) {
        User user;
        try {
            Session session = SessionUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Query<User> query = session.createQuery("FROM User u WHERE u.username=:username AND u.password=:password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password);
            user = query.getSingleResult();

            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            throw LoginException.builder()
                    .withMessage("No user by entered username and pass was found.")
                    .withErrorCode(404).build();
        }
        return user;
    }
}
