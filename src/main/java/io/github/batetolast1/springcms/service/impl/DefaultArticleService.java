package io.github.batetolast1.springcms.service.impl;

import io.github.batetolast1.springcms.dto.ArticleDto;
import io.github.batetolast1.springcms.model.Article;
import io.github.batetolast1.springcms.repository.ArticleRepository;
import io.github.batetolast1.springcms.service.ArticleService;
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
public class DefaultArticleService implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ArticleDto> getLastArticles() {
        return articleRepository
                .findFirst5ByDraftFalseOrderByCreatedOnDesc()
                .stream()
                .map(a -> modelMapper.map(a, ArticleDto.class))
                .sorted(Comparator.comparing(ArticleDto::getCreatedOn).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> getAll() {
        return articleRepository
                .findAllByDraftFalse()
                .stream()
                .map(a -> modelMapper.map(a, ArticleDto.class))
                .sorted(Comparator.comparing(ArticleDto::getCreatedOn).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (existsAndIsArticle(id)) {
            articleRepository.deleteById(id);
        }
    }

    @Override
    public void save(ArticleDto articleDto) {
        Article article = modelMapper.map(articleDto, Article.class);
        article.setDraft(false);
        articleRepository.save(article);
    }

    @Override
    public ArticleDto getById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findByIdAndDraftIsFalse(id);
        return optionalArticle
                .map(a -> modelMapper.map(a, ArticleDto.class))
                .orElse(null);
    }

    @Override
    public void edit(ArticleDto articleDto) {
        Article article = modelMapper.map(articleDto, Article.class);

        if (existsAndIsArticle(article.getId())) {
            articleRepository.save(article);
        }
    }

    private boolean existsAndIsArticle(Long id) {
        return articleRepository.findByIdAndDraftIsFalse(id).isPresent();
    }
}
