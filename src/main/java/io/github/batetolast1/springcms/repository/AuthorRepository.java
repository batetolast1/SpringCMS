package io.github.batetolast1.springcms.repository;

import io.github.batetolast1.springcms.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
