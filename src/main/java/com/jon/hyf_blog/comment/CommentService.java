package com.jon.hyf_blog.comment;

import com.jon.hyf_blog.article.Article;
import com.jon.hyf_blog.article.ArticleRepository;
import com.jon.hyf_blog.article.articleDTO.ArticleMapper;
import com.jon.hyf_blog.article.articleDTO.ArticleSummaryDTO;
import com.jon.hyf_blog.comment.commentDTO.CommentMapper;
import com.jon.hyf_blog.comment.commentDTO.CommentRequestDTO;
import com.jon.hyf_blog.comment.commentDTO.CommentResponseDTO;
import com.jon.hyf_blog.comment.commentDTO.CommentSummaryDTO;
import com.jon.hyf_blog.user.User;
import com.jon.hyf_blog.user.userDTO.UserMapper;
import com.jon.hyf_blog.user.userDTO.UserSummaryDTO;
import com.jon.hyf_blog.util.exceptionHandler.NoResourceException;
import com.jon.hyf_blog.util.exceptionHandler.ResourceNotFoundException;
import com.jon.hyf_blog.util.exceptionHandler.WrongResource;
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

    public CommentSummaryDTO save(
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

        return new CommentSummaryDTO(
                savedComment.getId(),
                savedComment.getBody(),
                articleSummaryDTO,
                userSummaryDTO
        );
    }

    public CommentSummaryDTO update(
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
        return commentMapper.toSummaryDto(updatedComment);
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
