package io.github.batetolast1.springcms.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "authors")
@Data
@EqualsAndHashCode(callSuper = true, of = {})
public class Author extends BaseEntity {

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @PrePersist
    @PreUpdate
    public void trim() {
        this.firstName = this.firstName.trim();
        this.lastName = this.lastName.trim();
    }

    @Override
    public void clearData() {
        super.clearData();
        this.firstName = "";
        this.lastName = "";
    }
}
