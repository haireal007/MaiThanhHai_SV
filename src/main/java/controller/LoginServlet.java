package controller;

import dao.AccountDao;
import model.Account;
import model.Login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    AccountDao accountDao = new AccountDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account = accountDao.getAccount(username, password);
        if (account != null) {
            Login.account = account;
            if (account.getRole().equals("user")) {
                resp.sendRedirect("/student");
            } else {
                resp.sendRedirect("/admin.jsp");
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, resp);
        }
    }
}
