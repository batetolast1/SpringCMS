package io.github.batetolast1.springcms.dao;

import io.github.batetolast1.springcms.model.Article;

import java.util.List;

public interface ArticleDao extends BaseDao<Article, Long> {

    List<Article> findFirst5ByOrderByCreatedOnDesc();
}
