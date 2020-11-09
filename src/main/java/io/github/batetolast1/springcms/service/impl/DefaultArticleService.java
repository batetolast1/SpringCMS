package io.github.batetolast1.springcms.service.impl;

import io.github.batetolast1.springcms.dao.ArticleDao;
import io.github.batetolast1.springcms.dto.ArticleDto;
import io.github.batetolast1.springcms.model.Article;
import io.github.batetolast1.springcms.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultArticleService implements ArticleService {

    private final ArticleDao articleDao;
    private final ModelMapper modelMapper;

    @Override
    public Set<ArticleDto> getLastArticles() {
        return articleDao
                .findFirst5ByOrderByCreatedOnDesc()
                .stream()
                .map(a -> modelMapper.map(a, ArticleDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ArticleDto> getAll() {
        return articleDao
                .findAll()
                .stream()
                .map(a -> modelMapper.map(a, ArticleDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(Long id) {
        articleDao.delete(id);
    }

    @Override
    public void save(ArticleDto articleDto) {
        Article article = modelMapper.map(articleDto, Article.class);
        article.setCreatedOn(LocalDateTime.now());
        articleDao.save(article);
    }

    @Override
    public ArticleDto getById(Long id) {
        Article article = articleDao.findById(id);
        return modelMapper.map(article, ArticleDto.class);
    }

    @Override
    public void edit(ArticleDto articleDto) {
        Article article = modelMapper.map(articleDto, Article.class);
        articleDao.update(article);
    }
}
