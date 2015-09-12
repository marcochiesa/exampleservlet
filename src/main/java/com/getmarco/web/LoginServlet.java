package com.getmarco.web;

import com.getmarco.util.LoginValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
    public static final String PATH_INDEX = "index.html";
    public static final String PATH_WELCOME = "/welcome";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(PATH_INDEX);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(PARAM_USERNAME);
        String password = req.getParameter(PARAM_PASSWORD);

        if (LoginValidation.authenticate(username, password)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(PATH_WELCOME);
            if (dispatcher == null) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }
            dispatcher.forward(req, resp);
            return;
        }

        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
