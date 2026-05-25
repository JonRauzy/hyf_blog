package com.jon.hyf_blog.comment;

import com.jon.hyf_blog.comment.commentDTO.CommentMapper;
import com.jon.hyf_blog.comment.commentDTO.CommentResponseDTO;
import com.jon.hyf_blog.comment.commentDTO.CommentSummaryDTO;
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
            throw new RuntimeException("TO DO : NO COMMENT IN DB EXCEPTION");
        }

        return commentList.stream()
                .map(commentMapper::toDto)
                .toList();
    }
}
