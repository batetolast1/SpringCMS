package io.github.batetolast1.springcms.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ArticleDto {

    private Long id;

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    @Size(max = 500)
    private String content;

    private LocalDateTime createdOn;

    @NotNull
    private AuthorDto authorDto;

    @NotEmpty
    private Set<CategoryDto> categoryDtos;
}
