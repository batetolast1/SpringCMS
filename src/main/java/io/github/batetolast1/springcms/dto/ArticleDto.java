package io.github.batetolast1.springcms.dto;

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

    @NotBlank
    @Size(min = 5, max = 200)
    private String title;

    @NotBlank
    @Size(min = 5, max = 1000)
    private String content;

    private LocalDateTime createdOn;

    @NotNull
    @Valid
    private AuthorDto authorDto;

    @NotEmpty
    @Valid
    private Set<CategoryDto> categoryDtos;
}
