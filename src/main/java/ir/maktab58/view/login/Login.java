package ir.maktab58.view.login;

import ir.maktab58.dao.UserDao;
import ir.maktab58.entity.User;
import ir.maktab58.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Taban Soleymani
 */
public class Login extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            userService.getUserByUserAndPass(username, password);
            req.getRequestDispatcher("/welcome").forward(req, resp);
        } catch (RuntimeException e) {
            out.print(e.getMessage());
            req.getRequestDispatcher("index.html").include(req, resp);
        }
    }
}
