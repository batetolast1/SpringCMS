package io.github.batetolast1.springcms.repository;

import io.github.batetolast1.springcms.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
