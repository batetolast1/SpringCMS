package io.github.batetolast1.springcms.repository;

import io.github.batetolast1.springcms.model.Article;
import io.github.batetolast1.springcms.model.Author;
import io.github.batetolast1.springcms.model.Category;
import io.github.batetolast1.springcms.model.enums.EntityType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @EntityGraph(value = "Article.allFields")
    Set<Article> findFirst5ByDraftFalseAndEntityTypeOrderByCreatedOnDesc(EntityType entityType);

    @EntityGraph(value = "Article.allFields")
    Set<Article> findAllByDraftFalseAndEntityType(EntityType entityType);

    @EntityGraph(value = "Article.allFields")
    Set<Article> findAllByDraftTrueAndEntityType(EntityType entityType);

    @EntityGraph(value = "Article.allFields")
    Set<Article> findAllByAuthorAndEntityType(Author author, EntityType entityType);

    @EntityGraph(value = "Article.allFields")
    Set<Article> findAllByCategoriesContainingAndEntityType(Category category, EntityType entityType);

    Optional<Article> findByIdAndDraftFalseAndEntityType(Long id, EntityType entityType);

    Optional<Article> findByIdAndDraftTrueAndEntityType(Long id, EntityType entityType);

    @EntityGraph(value = "Article.allFields")
    Set<Article> findAllByDraftFalseAndEntityTypeAndCategoriesContaining(EntityType entityType, Category category);

    boolean existsByIdAndDraftFalseAndEntityType(Long id, EntityType entityType);

    boolean existsByIdAndDraftTrueAndEntityType(Long id, EntityType entityType);
}
