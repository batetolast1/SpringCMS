package io.github.batetolast1.springcms.repository;

import io.github.batetolast1.springcms.model.Article;
import io.github.batetolast1.springcms.model.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @EntityGraph(value = "Article.allFields")
    Set<Article> findFirst5ByDraftFalseOrderByCreatedOnDesc();

    @EntityGraph(value = "Article.allFields")
    Set<Article> findAllByDraftFalse();

    @EntityGraph(value = "Article.allFields")
    Set<Article> findAllByDraftTrue();

    Optional<Article> findByIdAndDraftIsFalse(Long id);

    Optional<Article> findByIdAndDraftIsTrue(Long id);

    @EntityGraph(value = "Article.allFields")
    Set<Article> findAllByDraftFalseAndCategoriesContaining(Category category);
}
