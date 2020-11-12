package io.github.batetolast1.springcms.repository;

import io.github.batetolast1.springcms.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Set<Article> findFirst5ByDraftFalseOrderByCreatedOnDesc();

    Set<Article> findAllByDraftFalse();

    Set<Article> findAllByDraftTrue();

    Optional<Article> findByIdAndDraftIsFalse(Long id);

    Optional<Article> findByIdAndDraftIsTrue(Long id);
}
