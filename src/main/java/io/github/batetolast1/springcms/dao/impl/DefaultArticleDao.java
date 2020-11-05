package io.github.batetolast1.springcms.dao.impl;

import io.github.batetolast1.springcms.dao.ArticleDao;
import io.github.batetolast1.springcms.model.Article;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class DefaultArticleDao implements ArticleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Article article) {
        entityManager.persist(article);
    }

    @Override
    public Article findById(Long id) {
        return entityManager.find(Article.class, id);
    }

    @Override
    public void update(Article article) {
        entityManager.merge(article);
    }

    @Override
    public void delete(Long id) {
        Article article = findById(id);
        entityManager.remove(entityManager.contains(article) ? article : entityManager.merge(article));
    }

    @Override
    public List<Article> findAll() {
        TypedQuery<Article> query = entityManager.createQuery("SELECT a FROM Article a", Article.class);
        return query.getResultList();
    }

    @Override
    public List<Article> findFirst5ByOrderByCreatedOnDesc() {
        TypedQuery<Article> query = entityManager.createQuery("SELECT a FROM Article a ORDER BY a.createdOn DESC", Article.class);
        return query.setMaxResults(5).getResultList();
    }
}
