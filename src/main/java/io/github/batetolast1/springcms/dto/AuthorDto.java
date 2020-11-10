package io.github.batetolast1.springcms.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AuthorDto {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 100)
    private String lastName;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
