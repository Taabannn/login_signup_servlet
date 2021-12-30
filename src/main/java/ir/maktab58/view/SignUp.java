package ir.maktab58.view;

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
public class SignUp extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        int id = userService.saveNewUser(username, password);

        if (id != 0)
            out.print("you've registered successfully");
        else {
            out.print("Invalid user or pass :(");
            req.getRequestDispatcher("signup.html").include(req, resp);
        }
    }
}
