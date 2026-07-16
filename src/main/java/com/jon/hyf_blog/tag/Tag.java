package com.jon.hyf_blog.tag;

import com.jon.hyf_blog.article.Article;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_name")
    @NotBlank
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles = new HashSet<>();
}