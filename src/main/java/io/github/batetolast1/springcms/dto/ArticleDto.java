package io.github.batetolast1.springcms.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdOn;
}
