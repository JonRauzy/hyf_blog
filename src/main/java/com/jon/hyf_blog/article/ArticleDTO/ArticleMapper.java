package com.jon.hyf_blog.article.ArticleDTO;

import com.jon.hyf_blog.article.Article;
import com.jon.hyf_blog.tag.Tag;
import com.jon.hyf_blog.tag.TagDTO.TagResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleMapper {

    public ArticleResponseDTO toDto(Article article) {
        List<TagSummaryDTO> tagDtos = article.getTags()
                .stream()
                .map(tag -> new TagSummaryDTO(tag.getId(), tag.getTagName()))
                .toList();

        return new ArticleResponseDTO(
                article.getId(),
                article.getTitle(),
                article.getBody(),
                tagDtos
        );
    }


    public Article toEntity(ArticleRequestDTO articleRequestDTO) {
        Article article = new Article();
        article.setTitle(articleRequestDTO.getTitle());
        article.setBody(articleRequestDTO.getBody());

        List<Tag> tags = new ArrayList<>();
        if (articleRequestDTO.getTagIds() != null) {
            for (Long tagId : articleRequestDTO.getTagIds()) {
                Tag tag = new Tag();
                tag.setId(tagId);
                tags.add(tag);
            }
        }
        article.setTags(tags);

        return article;
    }
}
