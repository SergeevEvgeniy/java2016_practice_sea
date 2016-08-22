package com.epam.cars.web;

import com.epam.cars.service.MakerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCarServlet extends HttpServlet {

    private static final String MAKERS = "makers";
    private final MakerService makerS = new MakerService();

    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute(MAKERS, makerS.getMakers());
        req.getRequestDispatcher("addCar.jsp").forward(req, resp);
    }
}
