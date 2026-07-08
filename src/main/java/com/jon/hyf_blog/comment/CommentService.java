package com.jon.hyf_blog.comment;

import com.jon.hyf_blog.article.Article;
import com.jon.hyf_blog.article.ArticleRepository;
import com.jon.hyf_blog.article.articleDTO.ArticleMapper;
import com.jon.hyf_blog.article.articleDTO.ArticleSummaryDTO;
import com.jon.hyf_blog.comment.commentDTO.CommentMapper;
import com.jon.hyf_blog.comment.commentDTO.CommentRequestDTO;
import com.jon.hyf_blog.comment.commentDTO.CommentResponseDTO;
import com.jon.hyf_blog.comment.commentDTO.CommentSummaryDTO;
import com.jon.hyf_blog.user.userDTO.UserMapper;
import com.jon.hyf_blog.user.userDTO.UserSummaryDTO;
import com.jon.hyf_blog.util.exceptionHandler.NoRessourceExeption;
import com.jon.hyf_blog.util.exceptionHandler.RessourceNotFoundExeption;
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
            throw new NoRessourceExeption(Comment.class);
        }

        return commentList.stream()
                .map(commentMapper::toDto)
                .toList();
    }

    public CommentResponseDTO findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundExeption(Comment.class, id));

        return commentMapper.toDto(comment);
    }

    public CommentSummaryDTO save(CommentRequestDTO commentRequestDTO) {
        Comment comment = commentMapper.toEntity(commentRequestDTO);
        Comment savedComment = commentRepository.save(comment);
        UserSummaryDTO userSummaryDTO = userMapper.toSummaryDTO(savedComment.getUser());
        ArticleSummaryDTO articleSummaryDTO = articleMapper.toArticleSummaryDto(savedComment.getArticle());
        return new CommentSummaryDTO(savedComment.getId(), savedComment.getBody(), articleSummaryDTO, userSummaryDTO);
    }

    public CommentSummaryDTO update(Long id, CommentRequestDTO commentRequestDTO) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundExeption(Comment.class, id));

        Article article = articleRepository.findById(commentRequestDTO.getArticleId())
                .orElseThrow(() -> new RessourceNotFoundExeption(Article.class, id));

        existingComment.setArticle(article);
        existingComment.setUser(commentRequestDTO.getUser());
        existingComment.setBody(commentRequestDTO.getBody());

        Comment updatedComment = commentRepository.save(existingComment);
        return commentMapper.toSummaryDto(updatedComment);
    }
}
