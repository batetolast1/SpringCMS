package io.github.batetolast1.springcms.service;

import io.github.batetolast1.springcms.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> getLastArticles();
}
