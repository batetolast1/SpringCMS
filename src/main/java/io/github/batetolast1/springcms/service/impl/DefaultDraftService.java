package io.github.batetolast1.springcms.service.impl;

import io.github.batetolast1.springcms.dao.ArticleDao;
import io.github.batetolast1.springcms.dto.ArticleDto;
import io.github.batetolast1.springcms.model.Article;
import io.github.batetolast1.springcms.service.DraftService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultDraftService implements DraftService {

    private final ArticleDao articleDao;
    private final ModelMapper modelMapper;

    @Override
    public List<ArticleDto> getAll() {
        return articleDao
                .findAllByDraftTrue()
                .stream()
                .map(a -> modelMapper.map(a, ArticleDto.class))
                .sorted(Comparator.comparing(ArticleDto::getCreatedOn))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (existsAndIsDraft(id)) {
            articleDao.delete(id);
        }
    }

    @Override
    public void save(ArticleDto draftDto) {
        Article draft = modelMapper.map(draftDto, Article.class);
        draft.setDraft(true);
        articleDao.save(draft);
    }

    @Override
    public ArticleDto getById(Long id) {
        if (existsAndIsDraft(id)) {
            Article draft = articleDao.findById(id);
            return modelMapper.map(draft, ArticleDto.class);
        }

        return null;
    }

    @Override
    public void edit(ArticleDto draftDto) {
        Article draft = modelMapper.map(draftDto, Article.class);

        if (existsAndIsDraft(draft.getId())) {
            articleDao.update(draft);
        }
    }

    @Override
    public void publish(ArticleDto draftDto) {
        Article draft = modelMapper.map(draftDto, Article.class);

        if (existsAndIsDraft(draft.getId())) {
            draft.setDraft(false);
            articleDao.update(draft);
        }
    }

    private boolean existsAndIsDraft(Long id) {
        Article article = articleDao.findById(id);
        return article != null && article.getDraft();
    }
}
