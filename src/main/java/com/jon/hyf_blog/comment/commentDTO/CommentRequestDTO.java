package com.jon.hyf_blog.comment.commentDTO;

import com.jon.hyf_blog.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentRequestDTO {
    private String body;
    private User user;
    private Long articleId;
    private Long userId;
}
