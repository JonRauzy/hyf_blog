package com.jon.hyf_blog.comment.commentDTO;

import com.jon.hyf_blog.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentSummaryDTO {
    private Long id;
    private String body;
    private String userName;
}
