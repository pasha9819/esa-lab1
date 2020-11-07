package ru.ssau.esa.servlets.delete;

import ru.ssau.esa.bean.FarmerDaoBean;
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

@WebServlet("/delete/farmer")
public class FarmerDelete extends HttpServlet {

    @EJB
    private FarmerDaoBean farmerDaoBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        long id;
        try{
            id = Long.parseLong(idStr);
        }catch (NumberFormatException e){
            resp.getWriter().println(BadResponse.badParameterConversion("id", idStr));
            return;
        }
        Farmer farmer = farmerDaoBean.findById(id);
        if (farmer == null){
            resp.getWriter().print(new BadResponse("Farmer not found"));
            return;
        }
        if (!farmer.getAnimals().isEmpty()){
            resp.getWriter().print(new BadResponse("Farmer has animals"));
            return;
        }
        farmerDaoBean.delete(farmer);
        resp.getWriter().print(new GoodResponse());
    }

}
