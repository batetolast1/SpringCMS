package io.github.batetolast1.springcms.dao.impl;

import io.github.batetolast1.springcms.dao.CategoryDao;
import io.github.batetolast1.springcms.model.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class DefaultCategoryDao implements CategoryDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Category category) {
        entityManager.persist(category);
    }

    @Override
    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public void update(Category category) {
        entityManager.merge(category);
    }

    @Override
    public void delete(Long id) {
        Category category = findById(id);
        entityManager.remove(entityManager.contains(category) ? category : entityManager.merge(category));
    }

    @Override
    public List<Category> findAll() {
        TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c", Category.class);
        return query.getResultList();
    }
}
