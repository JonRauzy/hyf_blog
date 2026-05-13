package com.jon.hyf_blog.article.articleDTO;

import com.jon.hyf_blog.article.Article;
import com.jon.hyf_blog.tag.Tag;
import com.jon.hyf_blog.tag.TagDTO.TagSummaryDTO;
import com.jon.hyf_blog.tag.TagRepository;
import com.jon.hyf_blog.tag.tagExeption.NoTagExeption;
import com.jon.hyf_blog.tag.tagExeption.TagNotFoundExeption;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleMapper {

    private final TagRepository tagRepository;

    public ArticleMapper(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

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
        List<Tag> tags = new ArrayList<>();

        article.setTitle(articleRequestDTO.getTitle());
        article.setBody(articleRequestDTO.getBody());

        if (articleRequestDTO.getTagIds() == null) {
            throw new NoTagExeption();
        }

        for (Long tagId : articleRequestDTO.getTagIds()) {
            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new TagNotFoundExeption(tagId));
            tags.add(tag);
        }

        article.setTags(tags);
        return article;
    }
}
