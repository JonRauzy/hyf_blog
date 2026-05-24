package com.jon.hyf_blog.comment.commentDTO;

import com.jon.hyf_blog.article.Article;
import com.jon.hyf_blog.article.ArticleRepository;
import com.jon.hyf_blog.article.articleDTO.ArticleMapper;
import com.jon.hyf_blog.article.articleDTO.ArticleSummaryDTO;
import com.jon.hyf_blog.article.articleExeption.ArticleNotFoundExeption;
import com.jon.hyf_blog.comment.Comment;
import com.jon.hyf_blog.user.User;
import com.jon.hyf_blog.user.UserRepository;
import com.jon.hyf_blog.user.userDTO.UserSummaryDTO;
import com.jon.hyf_blog.user.userExeption.UserNotFoundExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final UserRepository userRepository;
    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;

    public CommentResponseDTO toDto(Comment comment) {
        User user = comment.getUser();
        UserSummaryDTO userSummaryDTO = new UserSummaryDTO(
                user.getId(),
                user.getUserName(),
                user.getRole()
        );

        Article article = comment.getArticle();
        ArticleSummaryDTO articleSummaryDTO = new ArticleSummaryDTO(
                article.getId(),
                article.getTitle()
        );

        return new CommentResponseDTO(
                comment.getId(),
                comment.getBody(),
                comment.getCreatedAt(),
                userSummaryDTO,
                articleSummaryDTO
        );
    }

    public CommentSummaryDTO toSummaryDto(Comment comment) {
        return new CommentSummaryDTO(
                comment.getId(),
                comment.getBody()
        );
    }

    public Comment toEntity(CommentRequestDTO commentRequestDTO) {
        Comment comment = new Comment();
        Long articleId = commentRequestDTO.getArticleId();
        Long userId = commentRequestDTO.getUserId();

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundExeption(articleId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExeption(userId));

        comment.setBody(commentRequestDTO.getBody());
        comment.setArticle(article);
        comment.setUser(user);

        return comment;
    }
}
