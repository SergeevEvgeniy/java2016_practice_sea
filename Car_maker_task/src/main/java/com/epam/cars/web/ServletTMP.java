package com.epam.cars.web;

import com.epam.cars.CarMaker;
import static com.epam.cars.CarMaker.LOG;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTMP extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        LOG.info("i'm alive");
        //out.print("<h1>Hello World! </h1>");

    }
}
