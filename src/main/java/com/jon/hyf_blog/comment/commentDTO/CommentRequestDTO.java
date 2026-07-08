package com.jon.hyf_blog.comment.commentDTO;

import com.jon.hyf_blog.user.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentRequestDTO {
    @NotBlank
    private String body;

    @NotNull
    private User user;

    @NotNull
    @Min(1)
    private Long articleId;
}
