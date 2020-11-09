package io.github.batetolast1.springcms.dao;

import io.github.batetolast1.springcms.model.Article;

import java.util.Set;

public interface ArticleDao extends BaseDao<Article, Long> {

    Set<Article> findFirst5ByOrderByCreatedOnDesc();
}
