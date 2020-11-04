package io.github.batetolast1.springcms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articles")
@Data
public class Article extends BaseEntity {

    @Column(length = 200)
    private String title;
    // one to many?
    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Category> categorySet = new ArrayList<>();
    private String content;
}
