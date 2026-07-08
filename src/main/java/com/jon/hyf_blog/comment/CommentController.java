package com.jon.hyf_blog.comment;

import com.jon.hyf_blog.comment.commentDTO.CommentRequestDTO;
import com.jon.hyf_blog.comment.commentDTO.CommentResponseDTO;
import com.jon.hyf_blog.comment.commentDTO.CommentSummaryDTO;
import com.jon.hyf_blog.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{articleId}")
    public CommentSummaryDTO insertComment(
            @PathVariable Long articleId,
            @Valid @RequestBody CommentRequestDTO commentRequestDTO,
            @AuthenticationPrincipal User currentUser
    ) {
        commentRequestDTO.setArticleId(articleId);
        commentRequestDTO.setUser(currentUser);
        return commentService.save(commentRequestDTO);
    }
}
