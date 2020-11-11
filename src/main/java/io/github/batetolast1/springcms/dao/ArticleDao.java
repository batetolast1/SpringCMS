package io.github.batetolast1.springcms.dao;

import io.github.batetolast1.springcms.model.Article;

import java.util.List;
import java.util.Set;

public interface ArticleDao extends BaseDao<Article, Long> {

    List<Article> findFirst5ByDraftFalseByOrderByCreatedOnDesc();

    Set<Article> findAllByDraftTrue();
}
