package io.github.batetolast1.springcms.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "articles")
@Data
@EqualsAndHashCode(callSuper = true, of = {})
public class Article extends BaseEntity {

    @Column(length = 200)
    private String title;

    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(name = "article_categories",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categorySet = new HashSet<>();

    @Column(columnDefinition = "TEXT")
    private String content;
}
