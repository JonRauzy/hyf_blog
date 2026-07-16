package com.jon.hyf_blog.comment;

import com.jon.hyf_blog.comment.commentDTO.CommentRequestDTO;
import com.jon.hyf_blog.comment.commentDTO.CommentResponseDTO;
import com.jon.hyf_blog.comment.commentDTO.CommentSummaryDTO;
import com.jon.hyf_blog.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comments")
    public List<CommentResponseDTO> findAll(){
        return commentService.findAllWithArticleAndUser();
    }

    @GetMapping("/comments/{commentId}")
    public CommentResponseDTO findById(@PathVariable Long commentId) {
        return commentService.findById(commentId);
    }

//    @GetMapping("/article/{articleId}/comments")
//    public List<CommentResponseDTO> findAllByArticle(@PathVariable Long articleId){
//        return commentService.findAllByArticle();
//    }

//    @GetMapping("/article/{articleId}/comments/{commentId}")
//    public List<CommentResponseDTO> findByIdByArticle(@PathVariable Long articleId){
//        return commentService.findAllByArticle();
//    }

    @PostMapping("/articles/{articleId}/comments")
    public CommentSummaryDTO insertComment(
            @PathVariable Long articleId,
            @Valid @RequestBody CommentRequestDTO commentRequestDTO,
            @AuthenticationPrincipal User currentUser
    ) {
        return commentService.save(commentRequestDTO, articleId, currentUser);
    }

    @PutMapping("/articles/{articleId}/comments/{commentId}")
    public CommentSummaryDTO updateComment(
            @PathVariable Long commentId,
            @PathVariable Long articleId,
            @Valid @RequestBody CommentRequestDTO commentRequestDTO,
            @AuthenticationPrincipal User currentUser
    ) {

        return commentService.update(commentRequestDTO, commentId, articleId, currentUser);
    }

    @DeleteMapping("/articles/{articleId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long commentId,
            @PathVariable Long articleId,
            @AuthenticationPrincipal User currentUser
    ) {
        commentService.delete(commentId, articleId, currentUser);
        return ResponseEntity.ok("Comment id : " + commentId + " is deleted");
    }
}
