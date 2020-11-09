package io.github.batetolast1.springcms.service.impl;

import io.github.batetolast1.springcms.dao.AuthorDao;
import io.github.batetolast1.springcms.dto.AuthorDto;
import io.github.batetolast1.springcms.model.Author;
import io.github.batetolast1.springcms.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultAuthorService implements AuthorService {

    private final AuthorDao authorDao;
    private final ModelMapper modelMapper;

    @Override
    public Set<AuthorDto> getAll() {
        return authorDao
                .findAll()
                .stream()
                .map(c -> modelMapper.map(c, AuthorDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(Long id) {
        authorDao.delete(id);
    }

    @Override
    public void save(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorDao.save(author);
    }

    @Override
    public AuthorDto getById(Long id) {
        Author author = authorDao.findById(id);
        return modelMapper.map(author, AuthorDto.class);
    }

    @Override
    public void edit(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorDao.update(author);
    }
}
