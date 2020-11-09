package io.github.batetolast1.springcms.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ArticleDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdOn;
    private AuthorDto authorDto;
    private Set<CategoryDto> categoryDtos;
}
