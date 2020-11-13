package io.github.batetolast1.springcms.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
@EqualsAndHashCode(callSuper = true, of = {})
public class Category extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(100) UNIQUE")
    private String name;

    @Column(length = 200)
    private String description;

    @PrePersist
    @PreUpdate
    public void trim() {
        this.name = this.name.trim();
        this.description = this.description.trim();
    }

    @Override
    public void clearData() {
        super.clearData();
        this.name = "";
        this.description = "";
    }
}
