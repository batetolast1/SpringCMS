package io.github.batetolast1.springcms.converter;

import io.github.batetolast1.springcms.dto.CategoryDto;
import io.github.batetolast1.springcms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;


public class CategoryDtoConverter implements Converter<String, CategoryDto> {

    @Autowired
    private CategoryService categoryService;

    @Override
    public CategoryDto convert(String s) {
        long id = Long.parseLong(s);
        return categoryService.getById(id);
    }
}
