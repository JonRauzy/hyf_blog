package com.jon.hyf_blog.article.ArticleDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleRequestDTO {
    private String title;
    private String body;
    private List<Long> tagIds;
}
