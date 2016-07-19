package com.epam.cars.web;

import com.epam.cars.CarRepo;
import com.epam.cars.CarRepository;
import com.epam.cars.model.Car;
import com.epam.cars.model.Maker;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCarServlet extends HttpServlet {

    private final CarRepository repo = CarRepo.getInstance();
    private static final String ID = "Id";
    private static final String CAR = "car";
    private String id;

    @Override
    protected void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        id = req.getParameter(ID);
        req.setAttribute(CAR, repo.getCar(id));
        req.getRequestDispatcher("edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        Car car = new Car(new Maker(req.getParameter("Concern_Name_TB"),
                req.getParameter("Concern_Adres_TB"),
                Integer.parseInt(req.getParameter("Concern_FoundYear_TB"))),
                req.getParameter("Car_Model_TB"),
                Integer.parseInt(req.getParameter("Car_Year_TB")),
                req.getParameter("Car_Color_TB"));

        repo.updateCar(id, car);

        resp.sendRedirect("/Car_maker_task/list");
    }
}
