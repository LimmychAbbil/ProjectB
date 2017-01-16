package net.lim;

import net.lim.strategies.Strategy;
import net.lim.strategies.XLSStrategy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Limmy on 22.12.2016.
 */
public class Servlet extends javax.servlet.http.HttpServlet {

    Strategy usersBase;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            usersBase = XLSStrategy.getInstance();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUsersBase(Strategy usersBase) {
        this.usersBase = usersBase;
    }
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        String name = request.getParameter("name");

        int attempts = usersBase.read(name);
        if (attempts == 0) {
            usersBase.create(name);
        }
        else {
            usersBase.update(name);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/result");
        request.setAttribute("userName", name);
        request.setAttribute("attempts", attempts + 1);
        dispatcher.forward(request, response);
    }
}
