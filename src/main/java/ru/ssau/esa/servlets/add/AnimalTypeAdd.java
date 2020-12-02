package ru.ssau.esa.servlets.add;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ssau.esa.bean.AnimalTypeDaoBean;
import ru.ssau.esa.entity.AnimalType;
import ru.ssau.esa.response.GoodResponse;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add/animalType")
public class AnimalTypeAdd extends HttpServlet {

    @EJB
    private AnimalTypeDaoBean animalTypeDaoBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String name = req.getParameter("name");
        AnimalType type = new AnimalType();
        type.setName(name);
        AnimalType t = animalTypeDaoBean.add(type);
        ObjectMapper mapper = new ObjectMapper();
        String answer = mapper.writeValueAsString(t);
        resp.getWriter().println(new GoodResponse(answer));
    }
}