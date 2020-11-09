package io.github.batetolast1.springcms.service;

import io.github.batetolast1.springcms.dto.CategoryDto;

import java.util.Set;

public interface CategoryService {

    Set<CategoryDto> getAll();

    void delete(Long id);

    void save(CategoryDto categoryDto);

    CategoryDto getById(Long id);

    void edit(CategoryDto categoryDto);
}
