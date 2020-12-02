package ru.ssau.esa.servlets.delete;

import ru.ssau.esa.bean.AnimalTypeDaoBean;
import ru.ssau.esa.entity.AnimalType;
import ru.ssau.esa.response.BadResponse;
import ru.ssau.esa.response.GoodResponse;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete/animalType")
public class AnimalTypeDelete extends HttpServlet {

    @EJB
    private AnimalTypeDaoBean animalTypeDaoBean;

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
        AnimalType animalType = animalTypeDaoBean.findById(id);
        if (animalType == null){
            resp.getWriter().print(new BadResponse("AnimalType not found"));
            return;
        }
        if (!animalType.getAnimals().isEmpty()){
            resp.getWriter().print(new BadResponse("This type includes at least one animal"));
            return;
        }
        animalTypeDaoBean.delete(animalType);
        resp.getWriter().print(new GoodResponse());
    }

}
