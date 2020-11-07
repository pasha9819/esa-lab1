package ru.ssau.esa.bean;

import ru.ssau.esa.dao.SimpleDao;
import ru.ssau.esa.entity.AnimalType;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
@LocalBean
public class AnimalTypeDaoBean implements SimpleDao<AnimalType, Long> {

    @PersistenceContext(unitName = "esa")
    private EntityManager em;

    @Override
    public AnimalType findById(Long id) {
        return em.find(AnimalType.class, id);
    }

    @Override
    public AnimalType add(AnimalType entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(AnimalType entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public List<AnimalType> findAll() {
        return em.createQuery("SELECT t FROM AnimalType t", AnimalType.class).getResultList();
    }
}
