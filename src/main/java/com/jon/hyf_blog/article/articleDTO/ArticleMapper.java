package com.jon.hyf_blog.article.articleDTO;

import com.jon.hyf_blog.article.Article;
import com.jon.hyf_blog.comment.commentDTO.CommentSummaryDTO;
import com.jon.hyf_blog.tag.Tag;
import com.jon.hyf_blog.tag.TagDTO.TagSummaryDTO;
import com.jon.hyf_blog.tag.TagRepository;
import com.jon.hyf_blog.user.User;
import com.jon.hyf_blog.user.UserRepository;
import com.jon.hyf_blog.user.userDTO.UserSummaryDTO;
import com.jon.hyf_blog.util.exceptionHandler.NoRessourceExeption;
import com.jon.hyf_blog.util.exceptionHandler.RessourceNotFoundExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ArticleMapper {

    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public ArticleResponseDTO toDto(Article article) {
        List<TagSummaryDTO> tagDtos = article.getTags()
                .stream()
                .map(tag -> new TagSummaryDTO(tag.getId(), tag.getTagName()))
                .toList();

        User user = article.getUser();
        UserSummaryDTO userDTO = new UserSummaryDTO(
                article.getUser().getId(),
                article.getUser().getUserName(),
                article.getUser().getRole()
        );

        List<CommentSummaryDTO> commentDTO = article.getComments()
                .stream()
                .map(comment -> new CommentSummaryDTO(
                        comment.getId(),
                        comment.getBody(),
                        comment.getUser().getUserName()))
                .toList();

        return new ArticleResponseDTO(
                article.getId(),
                article.getTitle(),
                article.getBody(),
                userDTO,
                tagDtos,
                commentDTO
        );
    }

    public ArticleSummaryDTO toArticleSummaryDto(Article article) {
        ArticleSummaryDTO summary = new ArticleSummaryDTO(article.getId(), article.getTitle());
        summary.setId(article.getId());
        summary.setTitle(article.getTitle());
        return summary;
    }

    public Article toEntity(ArticleRequestDTO articleRequestDTO) {
        Article article = new Article();
        Set<Tag> tags = new HashSet<>();
        User user = userRepository.findById(articleRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("No user id : " + articleRequestDTO.getUserId()));

        article.setTitle(articleRequestDTO.getTitle());
        article.setBody(articleRequestDTO.getBody());
        article.setUser(user);

        if (articleRequestDTO.getTagIds() == null) {
            throw new NoRessourceExeption(Tag.class);
        }

        for (Long tagId : articleRequestDTO.getTagIds()) {
            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new RessourceNotFoundExeption(Tag.class, tagId));
            tags.add(tag);
        }

        article.setTags(tags);
        return article;
    }
}
