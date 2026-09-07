package com.jon.hyf_blog.comment.dto;

import com.jon.hyf_blog.article.ArticleRepository;
import com.jon.hyf_blog.article.dto.ArticleMapper;
import com.jon.hyf_blog.article.dto.ArticleSummaryDTO;
import com.jon.hyf_blog.comment.Comment;
import com.jon.hyf_blog.user.User;
import com.jon.hyf_blog.user.UserRepository;
import com.jon.hyf_blog.user.dto.UserMapper;
import com.jon.hyf_blog.user.dto.UserSummaryDTO;
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
                userSummaryDTO,
                comment.getCreatedAt()
        );
    }

    public Comment toEntity(CommentRequestDTO commentRequestDTO) {
        Comment comment = new Comment();
        comment.setBody(commentRequestDTO.getBody());
        return comment;
    }
}
