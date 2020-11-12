package io.github.batetolast1.springcms.service.impl;

import io.github.batetolast1.springcms.dto.CategoryDto;
import io.github.batetolast1.springcms.model.Category;
import io.github.batetolast1.springcms.repository.CategoryRepository;
import io.github.batetolast1.springcms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository
                .findAll()
                .stream()
                .map(c -> modelMapper.map(c, CategoryDto.class))
                .sorted(Comparator.comparing(CategoryDto::getName))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (exists(id)) {
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public CategoryDto getById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory
                .map(c -> modelMapper.map(c, CategoryDto.class))
                .orElse(null);
    }

    @Override
    public void edit(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);

        if (exists(category.getId())) {
            categoryRepository.save(category);
        }
    }

    private boolean exists(Long id) {
        return categoryRepository.existsById(id);
    }
}
