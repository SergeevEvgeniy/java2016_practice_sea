package com.epam.cars.web;

import com.epam.cars.model.Car;
import com.epam.cars.service.CarService;
import com.epam.cars.service.MakerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ListCarsServlet {

    private static final String MAKERS = "makers";
    private static final String CAR = "cars";
    @Autowired
    private  CarService carService ;
    @Autowired
    private MakerService makerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute(MAKERS, makerService.getMakers());
        req.setAttribute(CAR, carService.getCars());
        req.getRequestDispatcher("list.jsp").forward(req, resp);

    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        long selectedMaker = Long.parseLong(req.getParameter("concerns"));

        Car car = new Car(makerService.getMaker(selectedMaker),
                req.getParameter("Car_Model_TB"),
                Integer.parseInt(req.getParameter("Car_Year_TB")),
                req.getParameter("Car_Color_TB"));
        carService.saveCar(car);

        resp.sendRedirect("/Car_maker_task/list");
    }
}
