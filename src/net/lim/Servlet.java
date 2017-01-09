package net.lim;

import net.lim.strategies.Strategy;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Limmy on 22.12.2016.
 */
public class Servlet extends javax.servlet.http.HttpServlet {

    Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("Hello from servlet");

        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        String name = request.getParameter("name");
        if (!(name == null || name.isEmpty())) {
            session.setAttribute("name", name);
            context.setAttribute("name", name);
            System.out.println("Session attribute \"name\" is set");
        }

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("Your name maybe is " + name + "<br>");
        writer.println ("The session name is " + (String) session.getAttribute("name")+ "<br>");
        writer.println("Context attr \"name\" is " + (String) context.getAttribute("name") + "<br>");
        writer.flush();
        writer.close();

    }
}
