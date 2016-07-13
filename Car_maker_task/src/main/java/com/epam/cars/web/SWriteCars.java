package com.epam.cars.web;

import com.epam.cars.DefaultCarMaker;
import com.epam.cars.model.Car;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SWriteCars extends HttpServlet {

    private List<Car> cars;

    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        DefaultCarMaker dcm = new DefaultCarMaker();
        cars = dcm.getDefCars();

        //PrintWriter out = resp.getWriter();
        //out.println(req.getPathInfo().substring(1));
        //req.setAttribute("cars", cars);
        //req.getRequestDispatcher("writer.jsp").forward(req, resp);
        PrintWriter out = resp.getWriter();
        out.println(req.getParameter("Concern_Name_TB"));
        out.println(req.getParameter("Concern_Adres_TB"));
        out.println(req.getParameter("Concern_FoundYear_TB"));
        out.println(req.getParameter("Car_Model_TB"));
        out.println(req.getParameter("Car_Color_TB"));
        out.println(req.getParameter("Car_Year_TB"));

    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
        PrintWriter out = resp.getWriter();
        out.println(req.getParameter("Concern_Name_TB"));
        out.println(req.getParameter("Concern_Adres_TB"));
        out.println(req.getParameter("Concern_FoundYear_TB"));
        out.println(req.getParameter("Car_Model_TB"));
        out.println(req.getParameter("Car_Color_TB"));
        out.println(req.getParameter("Car_Year_TB"));

    }
}
