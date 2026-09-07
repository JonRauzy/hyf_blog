package com.jon.hyf_blog.comment;

import com.jon.hyf_blog.article.Article;
import com.jon.hyf_blog.article.ArticleRepository;
import com.jon.hyf_blog.article.dto.ArticleMapper;
import com.jon.hyf_blog.article.dto.ArticleSummaryDTO;
import com.jon.hyf_blog.comment.dto.CommentMapper;
import com.jon.hyf_blog.comment.dto.CommentRequestDTO;
import com.jon.hyf_blog.comment.dto.CommentResponseDTO;
import com.jon.hyf_blog.user.User;
import com.jon.hyf_blog.user.dto.UserMapper;
import com.jon.hyf_blog.user.dto.UserSummaryDTO;
import com.jon.hyf_blog.exception.NoResourceException;
import com.jon.hyf_blog.exception.ResourceNotFoundException;
import com.jon.hyf_blog.exception.WrongResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final CommentMapper commentMapper;
    private final UserMapper userMapper;
    private final ArticleMapper articleMapper;

    public List<CommentResponseDTO> findAllWithArticleAndUser() {
        List<Comment> commentList = commentRepository.findAllWithArticleAndUser();

        if(commentList.isEmpty()) {
            throw new NoResourceException(Comment.class);
        }

        return commentList.stream()
                .map(commentMapper::toDto)
                .toList();
    }

    public CommentResponseDTO findById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(Comment.class, commentId));

        return commentMapper.toDto(comment);
    }

    public CommentResponseDTO save(
                CommentRequestDTO commentRequestDTO,
                Long articleId,
                User currentUser
    ) {
        Article currentArticle = articleRepository.findById(articleId)
                        .orElseThrow(()-> new ResourceNotFoundException(Article.class, articleId));

        Comment comment = commentMapper.toEntity(commentRequestDTO);
        comment.setUser(currentUser);
        comment.setArticle(currentArticle);

        Comment savedComment = commentRepository.save(comment);

        UserSummaryDTO userSummaryDTO = userMapper.toSummaryDTO(savedComment.getUser());
        ArticleSummaryDTO articleSummaryDTO = articleMapper.toArticleSummaryDto(savedComment.getArticle());

        return new CommentResponseDTO(
                savedComment.getId(),
                savedComment.getBody(),
                savedComment.getCreatedAt(),
                userSummaryDTO,
                articleSummaryDTO
        );
    }

    public CommentResponseDTO update(
            CommentRequestDTO commentRequestDTO,
            Long commentId,
            Long articleId,
            User currentUser
    ) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(Comment.class, commentId));

        if(!articleId.equals(existingComment.getArticle().getId())) {
            throw new WrongResource(Article.class);
        }

        if(!existingComment.getUser().getId().equals(currentUser.getId())) {
            throw new WrongResource(User.class);
        }

        existingComment.setUser(currentUser);
        existingComment.setBody(commentRequestDTO.getBody());

        Comment updatedComment = commentRepository.save(existingComment);
        return commentMapper.toDto(updatedComment);
    }

    public void delete(
            Long commentId,
            Long articleId,
            User currentUser
    ) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(Comment.class, commentId));

        if(!currentUser.getId().equals(comment.getUser().getId())) {
            throw new WrongResource(User.class);
        }

        if(!articleId.equals(comment.getArticle().getId())) {
            throw new WrongResource(Article.class);
        }

        commentRepository.delete(comment);
    }
}
