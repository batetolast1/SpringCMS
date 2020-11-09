package io.github.batetolast1.springcms.converter;

import io.github.batetolast1.springcms.dto.AuthorDto;
import io.github.batetolast1.springcms.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class AuthorDtoConverter implements Converter<String, AuthorDto> {

    @Autowired
    private AuthorService authorService;

    @Override
    public AuthorDto convert(String s) {
        long id = Long.parseLong(s);
        return authorService.getById(id);
    }
}
