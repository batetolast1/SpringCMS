package io.github.batetolast1.springcms.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Data
@EqualsAndHashCode(callSuper = true, of = {})
public class Category extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(100) UNIQUE")
    private String name;

    @Column(length = 200)
    private String description;
}
