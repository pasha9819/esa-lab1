package ru.ssau.esa.servlets.add;

import com.google.gson.Gson;
import ru.ssau.esa.bean.FarmerDaoBean;
import ru.ssau.esa.entity.Farmer;
import ru.ssau.esa.response.GoodResponse;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add/farmer")
public class FarmerAdd extends HttpServlet {

    @EJB
    private FarmerDaoBean farmerDaoBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        Farmer farmer = new Farmer();
        farmer.setName(name);
        farmer.setSurname(surname);
        Farmer f = farmerDaoBean.add(farmer);
        resp.getWriter().println(new GoodResponse(new Gson().toJson(f)));
    }
}
