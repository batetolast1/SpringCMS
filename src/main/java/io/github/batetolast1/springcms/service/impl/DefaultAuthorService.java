package io.github.batetolast1.springcms.service.impl;

import io.github.batetolast1.springcms.dto.AuthorDto;
import io.github.batetolast1.springcms.model.Author;
import io.github.batetolast1.springcms.repository.AuthorRepository;
import io.github.batetolast1.springcms.service.AuthorService;
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
public class DefaultAuthorService implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AuthorDto> getAll() {
        return authorRepository
                .findAll()
                .stream()
                .map(c -> modelMapper.map(c, AuthorDto.class))
                .sorted(Comparator.comparing(AuthorDto::getFullName))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (exists(id)) {
            authorRepository.deleteById(id);
        }
    }

    @Override
    public void save(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorRepository.save(author);
    }

    @Override
    public AuthorDto getById(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        return optionalAuthor
                .map(a -> modelMapper.map(a, AuthorDto.class))
                .orElse(null);
    }

    @Override
    public void edit(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);

        if (exists(author.getId())) {
            authorRepository.save(author);
        }
    }

    private boolean exists(Long id) {
        return authorRepository.existsById(id);
    }
}
