package io.github.batetolast1.springcms.repository;

import io.github.batetolast1.springcms.model.Category;
import io.github.batetolast1.springcms.model.enums.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByEntityType(EntityType entityType);

    Optional<Category> findByIdAndEntityType(Long id, EntityType entityType);

    boolean existsByIdAndEntityType(Long id, EntityType entityType);
}
