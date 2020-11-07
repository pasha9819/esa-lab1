package ru.ssau.esa.bean;

import ru.ssau.esa.dao.SimpleDao;
import ru.ssau.esa.entity.Animal;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
@LocalBean
public class AnimalDaoBean implements SimpleDao<Animal, Long> {

    @PersistenceContext(unitName = "esa")
    private EntityManager em;

    @Override
    public Animal findById(Long id) {
        return em.find(Animal.class, id);
    }

    @Override
    public Animal add(Animal entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(Animal entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public List<Animal> findAll() {
        return em.createQuery("SELECT a FROM Animal a", Animal.class).getResultList();
    }
}
