package com.jon.hyf_blog.user.userDTO;

import com.jon.hyf_blog.article.articleDTO.ArticleSummaryDTO;
import com.jon.hyf_blog.user.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private Role role;
    private List<ArticleSummaryDTO> articles;

}




