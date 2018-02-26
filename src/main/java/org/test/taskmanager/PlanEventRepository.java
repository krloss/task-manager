package org.test.taskmanager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.test.taskmanager.model.Plan;

@Repository
@Transactional
public class PlanEventRepository implements SaveEventRepository<Plan> {
    @PersistenceContext
    EntityManager entityManager;

    public Plan beforeSave(Plan entity) { return entity; }
    
    @Override
    public Plan save(Plan entity) {
        beforeSave(entity);
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        afterSave(entity);
        return entity;
    }
    
    public Plan afterSave(Plan entity) { return entity; }
}
