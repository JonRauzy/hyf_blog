package com.jon.hyf_blog.article.articleDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    private String title;

    @NotBlank
    private String body;

    @NotNull
    @Size(min = 1)
    private List<Long> tagIds;
}
