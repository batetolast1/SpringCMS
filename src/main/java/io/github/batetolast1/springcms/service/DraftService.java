package io.github.batetolast1.springcms.service;

import io.github.batetolast1.springcms.dto.ArticleDto;

import java.util.List;

public interface DraftService {

    List<ArticleDto> getAll();

    void delete(Long id);

    void save(ArticleDto draftDto);

    ArticleDto getById(Long id);

    void edit(ArticleDto draftDto);

    void publish(ArticleDto draftDto);
}
