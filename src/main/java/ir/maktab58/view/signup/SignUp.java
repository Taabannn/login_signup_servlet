package ir.maktab58.view.signup;

import ir.maktab58.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.awt.SystemColor.info;

/**
 * @author Taban Soleymani
 */
public class SignUp extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            userService.saveNewUser(firstName, lastName, email, username, password);
            out.write("You've registered successfully.");
        } catch (RuntimeException e) {
            out.print(e.getMessage());
            req.getRequestDispatcher("signup.html").include(req, resp);
        }
    }
}
