package io.github.batetolast1.springcms.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CategoryDto {

    private Long id;

    @NotBlank
    @Size(min = 5)
    private String name;

    private String description;
}
