package com.epam.cars.web;

import com.epam.cars.filters.AuthenticationFilter;
import com.epam.cars.model.Car;
import com.epam.cars.service.CarService;
import com.epam.cars.service.MakerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditCarController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);
    private static final String ID = "Id";
    private static final String CAR = "car";
    private static final String MAKERS = "makers";
    @Autowired
    private CarService carService;
    @Autowired
    private MakerService makerService;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public void doGet(final HttpServletRequest req,
            final HttpServletResponse resp,
            @RequestParam(ID) final long id)
            throws ServletException, IOException {

        req.setAttribute(MAKERS, makerService.getMakers());

        try {
            req.setAttribute(CAR, carService.getCar(id));
            req.setAttribute(ID, id);
            req.getRequestDispatcher("editCar.jsp").forward(req, resp);
        } catch (NullPointerException ex) {
            LOG.info("No such car found. Forward to new");
            req.getRequestDispatcher("/new").forward(req, resp);
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        long selectedMaker = Long.parseLong(req.getParameter("concerns"));

        Car car = new Car(makerService.getMaker(selectedMaker),
                req.getParameter("Car_Model_TB"),
                Integer.parseInt(req.getParameter("Car_Year_TB")),
                req.getParameter("Car_Color_TB"));

        car.setId(Long.parseLong(req.getParameter("Id_H_TB")));
        carService.updateCar(car);

        resp.sendRedirect("/Car_maker_task/list");
    }
}
