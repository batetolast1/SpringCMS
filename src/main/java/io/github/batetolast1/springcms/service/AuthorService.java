package io.github.batetolast1.springcms.service;

import io.github.batetolast1.springcms.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAll();

    void delete(Long id);

    void save(AuthorDto authorDto);

    AuthorDto getById(Long id);

    void edit(AuthorDto authorDto);
}
