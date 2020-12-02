package ru.ssau.esa.servlets.list;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        List<Farmer> farmers = farmerDaoBean.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String answer = mapper.writeValueAsString(farmers);
        resp.getWriter().print(answer);
    }

}
