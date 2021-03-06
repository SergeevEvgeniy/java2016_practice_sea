package com.epam.cars.web;

import com.epam.cars.service.CarService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {

    private final CarService carS = new CarService();
    
    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("search.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("cars", carS.getCars(req.getParameter("Search_TB")));
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}
