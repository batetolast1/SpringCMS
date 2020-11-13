package io.github.batetolast1.springcms.service.impl;

import io.github.batetolast1.springcms.dto.AuthorDto;
import io.github.batetolast1.springcms.model.Article;
import io.github.batetolast1.springcms.model.Author;
import io.github.batetolast1.springcms.model.enums.EntityType;
import io.github.batetolast1.springcms.repository.ArticleRepository;
import io.github.batetolast1.springcms.repository.AuthorRepository;
import io.github.batetolast1.springcms.service.AuthorService;
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
public class DefaultAuthorService implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AuthorDto> getAll() {
        return authorRepository
                .findAllByEntityType(EntityType.ACTIVE)
                .stream()
                .map(c -> modelMapper.map(c, AuthorDto.class))
                .sorted(Comparator.comparing(AuthorDto::getFullName))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findByIdAndEntityType(id, EntityType.ACTIVE);

        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();

            Set<Article> articles = articleRepository.findAllByAuthorAndEntityType(author, EntityType.ACTIVE);
            articles.forEach(a -> {
                a.setAuthor(null);
                articleRepository.save(a);
            });

            author.clearData();
            author.setEntityType(EntityType.DELETED);
            authorRepository.save(author);
        }
    }

    @Override
    public void save(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        authorRepository.save(author);
    }

    @Override
    public AuthorDto getById(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findByIdAndEntityType(id, EntityType.ACTIVE);
        return optionalAuthor
                .map(a -> modelMapper.map(a, AuthorDto.class))
                .orElse(null);
    }

    @Override
    public void edit(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);

        if (existsAndIsActive(author.getId())) {
            authorRepository.save(author);
        }
    }

    private boolean existsAndIsActive(Long id) {
        return authorRepository.existsByIdAndEntityType(id, EntityType.ACTIVE);
    }
}
