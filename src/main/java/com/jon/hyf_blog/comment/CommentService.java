package com.jon.hyf_blog.comment;

import com.jon.hyf_blog.comment.commentDTO.CommentMapper;
import com.jon.hyf_blog.comment.commentDTO.CommentResponseDTO;
import com.jon.hyf_blog.util.exceptionHandler.NoRessourceExeption;
import com.jon.hyf_blog.util.exceptionHandler.RessourceNotFoundExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

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
}
