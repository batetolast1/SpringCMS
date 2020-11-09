package io.github.batetolast1.springcms.service;

import io.github.batetolast1.springcms.dto.ArticleDto;

import java.util.Set;

public interface ArticleService {

    Set<ArticleDto> getLastArticles();

    Set<ArticleDto> getAll();

    void delete(Long id);

    void save(ArticleDto articleDto);

    ArticleDto getById(Long id);

    void edit(ArticleDto articleDto);
}
