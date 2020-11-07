package ru.ssau.esa.servlets.list;

import com.google.gson.Gson;
import ru.ssau.esa.bean.AnimalDaoBean;
import ru.ssau.esa.entity.Animal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/animals")
public class AnimalList extends HttpServlet {

    @EJB
    private AnimalDaoBean animalDaoBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Animal> animals = animalDaoBean.findAll();
        Gson converter = new Gson();
        resp.getWriter().print(converter.toJson(animals));
    }

}
