package io.github.batetolast1.springcms.dao.impl;

import io.github.batetolast1.springcms.dao.AuthorDao;
import io.github.batetolast1.springcms.model.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional
public class DefaultAuthorDao implements AuthorDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Author author) {
        entityManager.persist(author);
    }

    @Override
    public Author findById(Long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public void update(Author author) {
        entityManager.merge(author);
    }

    @Override
    public void delete(Long id) {
        Author author = findById(id);
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }

    @Override
    public Set<Author> findAll() {
        return entityManager
                .createQuery("SELECT a FROM Author a", Author.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }
}
