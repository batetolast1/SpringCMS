package io.github.batetolast1.springcms.repository;

import io.github.batetolast1.springcms.model.Author;
import io.github.batetolast1.springcms.model.enums.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAllByEntityType(EntityType entityType);

    Optional<Author> findByIdAndEntityType(Long id, EntityType entityType);

    boolean existsByIdAndEntityType(Long id, EntityType entityType);
}
