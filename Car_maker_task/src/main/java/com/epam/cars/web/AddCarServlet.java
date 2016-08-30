package com.epam.cars.web;

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
public class AddCarServlet {

    private static final String MAKERS = "makers";
    @Autowired
    private MakerService makerService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute(MAKERS, makerService.getMakers());
        req.getRequestDispatcher("addCar.jsp").forward(req, resp);
    }
}
