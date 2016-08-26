package com.epam.cars.web;

import com.epam.cars.model.Car;
import com.epam.cars.service.CarService;
import com.epam.cars.service.MakerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EditCarServlet{

    private static final String ID = "Id";
    private static final String CAR = "car";
    private static final String MAKERS = "makers";
    private Long id;
    private final CarService carS = new CarService();
    private final MakerService makerS = new MakerService();

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        id = Long.parseLong(req.getParameter(ID));
        req.setAttribute(MAKERS, makerS.getMakers());
        req.setAttribute(CAR, carS.getCar(id));
        req.setAttribute(ID, id);
        req.getRequestDispatcher("editCar.jsp").forward(req, resp);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        long selectedMaker = Long.parseLong(req.getParameter("concerns"));

        Car car = new Car(makerS.getMaker(selectedMaker),
                req.getParameter("Car_Model_TB"),
                Integer.parseInt(req.getParameter("Car_Year_TB")),
                req.getParameter("Car_Color_TB"));

        car.setId(Long.parseLong(req.getParameter("Id_H_TB")));
        carS.updateCar(car);

        resp.sendRedirect("/Car_maker_task/list");
    }
}
