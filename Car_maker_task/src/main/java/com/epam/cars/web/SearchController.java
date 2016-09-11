package com.epam.cars.web;

import com.epam.cars.service.CarService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public void doGet(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("search.jsp").forward(req, resp);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("cars", carService.getCarsByModel(req.getParameter("Search_TB")));
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}
