package ru.ssau.esa.bean;

import ru.ssau.esa.dao.SimpleDao;
import ru.ssau.esa.entity.Farmer;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
@LocalBean
public class FarmerDaoBean implements SimpleDao<Farmer, Long> {

    @PersistenceContext(unitName = "esa")
    private EntityManager em;

    @Override
    public Farmer findById(Long id) {
        return em.find(Farmer.class, id);
    }

    @Override
    public Farmer add(Farmer entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(Farmer entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public List<Farmer> findAll() {
        return em.createQuery("SELECT f FROM Farmer f", Farmer.class).getResultList();
    }
}
