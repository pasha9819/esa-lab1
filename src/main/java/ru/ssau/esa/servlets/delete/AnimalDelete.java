package ru.ssau.esa.servlets.delete;

import ru.ssau.esa.bean.AnimalDaoBean;
import ru.ssau.esa.entity.Animal;
import ru.ssau.esa.response.BadResponse;
import ru.ssau.esa.response.GoodResponse;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete/animal")
public class AnimalDelete extends HttpServlet {

    @EJB
    private AnimalDaoBean animalDaoBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String idStr = req.getParameter("id");
        long id;
        try{
            id = Long.parseLong(idStr);
        }catch (NumberFormatException e){
            resp.getWriter().println(BadResponse.badParameterConversion("id", idStr));
            return;
        }
        Animal animal = animalDaoBean.findById(id);
        if (animal == null){
            resp.getWriter().print(new BadResponse("Animal not found"));
            return;
        }
        animalDaoBean.delete(animal);
        resp.getWriter().print(new GoodResponse());
    }

}