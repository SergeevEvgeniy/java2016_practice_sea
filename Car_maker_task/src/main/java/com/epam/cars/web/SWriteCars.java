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

    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        DefaultCarMaker dcm = new DefaultCarMaker();
        List<Car> cars = dcm.getDefCars();

        PrintWriter out = resp.getWriter();

        //out.println(req.getPathInfo().substring(1));

        /*    switch (req.getPathInfo()) {
         case "/list":
         break;
         case "/new":
         break;
         case "/search":
         break;
         }*/
        for (Car c : cars) {
            String outStr = c.getMaker().getName() + " " + c.getModel() + " "
                    + c.getYear() + " " + c.getColor();
            out.println("<p> " + outStr + "</p>");

            req.setAttribute("cars", cars);
            req.getRequestDispatcher("writer.jsp").forward(req, resp);
        }
    }
}
