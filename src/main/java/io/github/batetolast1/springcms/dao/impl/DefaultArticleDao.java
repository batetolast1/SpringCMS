package io.github.batetolast1.springcms.dao.impl;

import io.github.batetolast1.springcms.dao.ArticleDao;
import io.github.batetolast1.springcms.model.Article;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<Article> findAll() {
        return entityManager
                .createQuery("SELECT a FROM Article a WHERE a.draft = false", Article.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public List<Article> findFirst5ByDraftFalseOrderByCreatedOnDesc() {
        return entityManager
                .createQuery("SELECT a FROM Article a WHERE a.draft = false ORDER BY a.createdOn DESC", Article.class)
                .setMaxResults(5)
                .getResultList();
    }

    @Override
    public Set<Article> findAllByDraftTrue() {
        return entityManager
                .createQuery("SELECT a FROM Article a WHERE a.draft = true", Article.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }
}
