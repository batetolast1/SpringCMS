package io.github.batetolast1.springcms.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Data
public class Category extends BaseEntity {

    @Column(unique = true, length = 100)
    private String name;
    private String description;
}
