package ru.ssau.esa.servlets.list;

import com.google.gson.Gson;
import ru.ssau.esa.bean.FarmerDaoBean;
import ru.ssau.esa.entity.Farmer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/farmers")
public class FarmerList extends HttpServlet {

    @EJB
    private FarmerDaoBean farmerDaoBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Farmer> farmers = farmerDaoBean.findAll();
        Gson converter = new Gson();
        resp.getWriter().print(converter.toJson(farmers));
    }

}
