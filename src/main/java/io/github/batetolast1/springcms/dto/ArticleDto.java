package io.github.batetolast1.springcms.dto;

import io.github.batetolast1.springcms.validation.groups.ArticleData;
import io.github.batetolast1.springcms.validation.groups.DraftData;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ArticleDto {

    private Long id;

    @NotBlank(groups = {DraftData.class, ArticleData.class})
    @Size(min = 5, max = 200, groups = {DraftData.class, ArticleData.class})
    private String title;

    @NotBlank(groups = {DraftData.class, ArticleData.class})
    @Size(min = 5, max = 1000, groups = {DraftData.class, ArticleData.class})
    private String content;

    private LocalDateTime createdOn;

    @NotNull(groups = ArticleData.class)
    @Valid
    private AuthorDto authorDto;

    @NotEmpty(groups = ArticleData.class)
    @Valid
    private Set<CategoryDto> categoryDtos;

    Boolean draft;
}
