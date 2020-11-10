package io.github.batetolast1.springcms.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthorDto {

    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
