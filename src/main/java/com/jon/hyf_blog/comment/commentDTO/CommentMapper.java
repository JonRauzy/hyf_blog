package com.jon.hyf_blog.comment.commentDTO;

import com.jon.hyf_blog.article.Article;
import com.jon.hyf_blog.article.ArticleRepository;
import com.jon.hyf_blog.article.articleDTO.ArticleMapper;
import com.jon.hyf_blog.article.articleDTO.ArticleSummaryDTO;
import com.jon.hyf_blog.comment.Comment;
import com.jon.hyf_blog.user.User;
import com.jon.hyf_blog.user.UserRepository;
import com.jon.hyf_blog.user.userDTO.UserMapper;
import com.jon.hyf_blog.user.userDTO.UserSummaryDTO;
import com.jon.hyf_blog.util.exceptionHandler.RessourceNotFoundExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public CommentResponseDTO toDto(Comment comment) {
        User user = comment.getUser();
        UserSummaryDTO userSummaryDTO = new UserSummaryDTO(
                user.getId(),
                user.getUserName(),
                user.getRole()
        );

        ArticleSummaryDTO articleSummaryDTO = articleMapper.toArticleSummaryDto(comment.getArticle());

        return new CommentResponseDTO(
                comment.getId(),
                comment.getBody(),
                comment.getCreatedAt(),
                userSummaryDTO,
                articleSummaryDTO
        );
    }

    public CommentSummaryDTO toSummaryDto(Comment comment) {
        UserSummaryDTO userSummaryDTO = userMapper.toSummaryDTO(comment.getUser());
        ArticleSummaryDTO articleSummaryDTO = articleMapper.toArticleSummaryDto(comment.getArticle());

        return new CommentSummaryDTO(
                comment.getId(),
                comment.getBody(),
                articleSummaryDTO,
                userSummaryDTO
        );
    }

    public Comment toEntity(CommentRequestDTO commentRequestDTO) {
        Comment comment = new Comment();
        Long articleId = commentRequestDTO.getArticleId();
        User user = commentRequestDTO.getUser();

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RessourceNotFoundExeption(Article.class, articleId));
        if(user == null) {
            throw new RessourceNotFoundExeption(User.class, 0L);
        }

        comment.setBody(commentRequestDTO.getBody());
        comment.setArticle(article);
        comment.setUser(user);

        return comment;
    }
}
