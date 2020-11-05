package io.github.batetolast1.springcms.service.impl;

import io.github.batetolast1.springcms.dao.CategoryDao;
import io.github.batetolast1.springcms.dto.CategoryDto;
import io.github.batetolast1.springcms.model.Category;
import io.github.batetolast1.springcms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        categoryDao.delete(id);
    }

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryDao.save(category);
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryDao.findById(id);
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void edit(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryDao.update(category);
    }
}
