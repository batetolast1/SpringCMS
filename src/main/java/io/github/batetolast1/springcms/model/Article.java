package io.github.batetolast1.springcms.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articles")
@Data
@EqualsAndHashCode(callSuper = true)
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
    private List<Category> categoryList = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String content;
}
