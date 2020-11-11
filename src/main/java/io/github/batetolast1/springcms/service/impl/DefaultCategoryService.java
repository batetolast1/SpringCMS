package io.github.batetolast1.springcms.service.impl;

import io.github.batetolast1.springcms.dao.CategoryDao;
import io.github.batetolast1.springcms.dto.CategoryDto;
import io.github.batetolast1.springcms.model.Category;
import io.github.batetolast1.springcms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultCategoryService implements CategoryService {

    private final CategoryDao categoryDao;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDto> getAll() {
        return categoryDao
                .findAll()
                .stream()
                .map(c -> modelMapper.map(c, CategoryDto.class))
                .sorted(Comparator.comparing(CategoryDto::getName))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (exists(id)) {
            categoryDao.delete(id);
        }
    }

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryDao.save(category);
    }

    @Override
    public CategoryDto getById(Long id) {
        if (exists(id)) {
            Category category = categoryDao.findById(id);
            return modelMapper.map(category, CategoryDto.class);
        }

        return null;
    }

    @Override
    public void edit(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);

        if (exists(category.getId())) {
            categoryDao.update(category);
        }
    }

    private boolean exists(Long id) {
        return categoryDao.findById(id) != null;
    }
}
