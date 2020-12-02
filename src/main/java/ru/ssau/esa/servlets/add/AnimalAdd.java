package ru.ssau.esa.servlets.add;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ssau.esa.bean.AnimalDaoBean;
import ru.ssau.esa.bean.AnimalTypeDaoBean;
import ru.ssau.esa.bean.FarmerDaoBean;
import ru.ssau.esa.entity.Animal;
import ru.ssau.esa.entity.AnimalType;
import ru.ssau.esa.entity.Farmer;
import ru.ssau.esa.response.BadResponse;
import ru.ssau.esa.response.GoodResponse;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add/animal")
public class AnimalAdd extends HttpServlet {

    @EJB
    private AnimalDaoBean animalDaoBean;
    @EJB
    private AnimalTypeDaoBean animalTypeDaoBean;
    @EJB
    private FarmerDaoBean farmerDaoBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String name = req.getParameter("name");
        String weightStr = req.getParameter("weight");
        String farmerIdStr = req.getParameter("farmerId");
        String animalTypeIdStr = req.getParameter("animalTypeId");
        double weight;
        long farmerId, animalTypeId;

        try{
            weight = Double.parseDouble(weightStr);
        }catch (NumberFormatException e){
            resp.getWriter().println(BadResponse.badParameterConversion("weight", weightStr));
            return;
        }
        try{
            farmerId = Long.parseLong(farmerIdStr);
        }catch (NumberFormatException e){
            resp.getWriter().println(BadResponse.badParameterConversion("farmerId", farmerIdStr));
            return;
        }
        try{
            animalTypeId = Long.parseLong(animalTypeIdStr);
        }catch (NumberFormatException e){
            resp.getWriter().println(BadResponse.badParameterConversion("animalTypeId", animalTypeIdStr));
            return;
        }

        Animal animal = new Animal();
        animal.setName(name);
        animal.setWeight(weight);

        Farmer f = farmerDaoBean.findById(farmerId);
        if (f == null){
            resp.getWriter().println(new BadResponse("Farmer not found"));
            return;
        }
        animal.setFarmer(f);

        AnimalType t = animalTypeDaoBean.findById(animalTypeId);
        if (t == null){
            resp.getWriter().println(new BadResponse("AnimalType not found"));
            return;
        }
        animal.setAnimalType(t);

        Animal newAnimal = animalDaoBean.add(animal);
        ObjectMapper mapper = new ObjectMapper();
        String answer = mapper.writeValueAsString(newAnimal);
        resp.getWriter().print(new GoodResponse(answer));
    }

}