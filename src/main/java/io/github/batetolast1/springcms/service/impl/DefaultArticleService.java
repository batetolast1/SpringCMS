package io.github.batetolast1.springcms.service.impl;

import io.github.batetolast1.springcms.dao.ArticleDao;
import io.github.batetolast1.springcms.dto.ArticleDto;
import io.github.batetolast1.springcms.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultArticleService implements ArticleService {

    private final ArticleDao articleDao;
    private final ModelMapper modelMapper;

    @Override
    public List<ArticleDto> getLastArticles() {
        return articleDao
                .findFirst5ByOrderByCreatedOnDesc()
                .stream()
                .map(a -> modelMapper.map(a, ArticleDto.class))
                .collect(Collectors.toList());
    }
}
