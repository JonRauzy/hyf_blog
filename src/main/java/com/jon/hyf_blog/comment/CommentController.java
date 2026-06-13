package com.jon.hyf_blog.comment;

import com.jon.hyf_blog.comment.commentDTO.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentResponseDTO> findAll(){
        return commentService.findAllWithArticleAndUser();
    }

    @GetMapping("/{id}")
    public CommentResponseDTO findById(@PathVariable Long id) {
        return commentService.findById(id);
    }
}
