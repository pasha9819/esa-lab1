package ru.ssau.esa.servlets.list;

import com.google.gson.Gson;
import ru.ssau.esa.bean.AnimalTypeDaoBean;
import ru.ssau.esa.entity.AnimalType;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/animalTypes")
public class AnimalTypeList extends HttpServlet {

    @EJB
    private AnimalTypeDaoBean animalTypeDaoBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AnimalType> animalTypes = animalTypeDaoBean.findAll();
        Gson converter = new Gson();
        resp.getWriter().print(converter.toJson(animalTypes));
    }

}
