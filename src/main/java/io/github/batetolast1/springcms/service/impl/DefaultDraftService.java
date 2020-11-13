package io.github.batetolast1.springcms.service.impl;

import io.github.batetolast1.springcms.dto.ArticleDto;
import io.github.batetolast1.springcms.model.Article;
import io.github.batetolast1.springcms.model.enums.EntityType;
import io.github.batetolast1.springcms.repository.ArticleRepository;
import io.github.batetolast1.springcms.service.DraftService;
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
public class DefaultDraftService implements DraftService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ArticleDto> getAll() {
        return articleRepository
                .findAllByDraftTrueAndEntityType(EntityType.ACTIVE)
                .stream()
                .map(a -> modelMapper.map(a, ArticleDto.class))
                .sorted(Comparator.comparing(ArticleDto::getCreatedOn))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (existsAndIsDraftAndIsActive(id)) {
            articleRepository.deleteById(id);
        }
    }

    @Override
    public void save(ArticleDto draftDto) {
        Article draft = modelMapper.map(draftDto, Article.class);
        draft.setDraft(true);
        articleRepository.save(draft);
    }

    @Override
    public ArticleDto getById(Long id) {
        Optional<Article> optionalDraft = articleRepository.findByIdAndDraftTrueAndEntityType(id, EntityType.ACTIVE);
        return optionalDraft
                .map(a -> modelMapper.map(a, ArticleDto.class))
                .orElse(null);
    }

    @Override
    public void edit(ArticleDto draftDto) {
        Article draft = modelMapper.map(draftDto, Article.class);

        if (existsAndIsDraftAndIsActive(draft.getId())) {
            articleRepository.save(draft);
        }
    }

    @Override
    public void publish(ArticleDto draftDto) {
        Article draft = modelMapper.map(draftDto, Article.class);

        if (existsAndIsDraftAndIsActive(draft.getId())) {
            draft.setDraft(false);
            articleRepository.save(draft);
        }
    }

    private boolean existsAndIsDraftAndIsActive(Long id) {
        return articleRepository.existsByIdAndDraftTrueAndEntityType(id, EntityType.ACTIVE);
    }
}
