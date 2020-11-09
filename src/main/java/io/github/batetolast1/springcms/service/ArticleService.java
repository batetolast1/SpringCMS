package io.github.batetolast1.springcms.service;

import io.github.batetolast1.springcms.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> getLastArticles();

    List<ArticleDto> getAll();

    void delete(Long id);

    void save(ArticleDto articleDto);

    ArticleDto getById(Long id);

    void edit(ArticleDto articleDto);
}
