package io.github.batetolast1.springcms.service.impl;

import io.github.batetolast1.springcms.dto.CategoryDto;
import io.github.batetolast1.springcms.model.Article;
import io.github.batetolast1.springcms.model.Category;
import io.github.batetolast1.springcms.model.enums.EntityType;
import io.github.batetolast1.springcms.repository.ArticleRepository;
import io.github.batetolast1.springcms.repository.CategoryRepository;
import io.github.batetolast1.springcms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository
                .findAllByEntityType(EntityType.ACTIVE)
                .stream()
                .map(c -> modelMapper.map(c, CategoryDto.class))
                .sorted(Comparator.comparing(CategoryDto::getName))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findByIdAndEntityType(id, EntityType.ACTIVE);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            Set<Article> articles = articleRepository.findAllByCategoriesContainingAndEntityType(category, EntityType.ACTIVE);
            articles.forEach(a -> {
                a.getCategories().remove(category);
                articleRepository.save(a);
            });

            category.clearData();
            category.setEntityType(EntityType.DELETED);
            categoryRepository.save(category);
        }
    }

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public CategoryDto getById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findByIdAndEntityType(id, EntityType.ACTIVE);
        return optionalCategory
                .map(c -> modelMapper.map(c, CategoryDto.class))
                .orElse(null);
    }

    @Override
    public void edit(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);

        if (existsAndIsActive(category.getId())) {
            categoryRepository.save(category);
        }
    }

    private boolean existsAndIsActive(Long id) {
        return categoryRepository.existsByIdAndEntityType(id, EntityType.ACTIVE);
    }
}
