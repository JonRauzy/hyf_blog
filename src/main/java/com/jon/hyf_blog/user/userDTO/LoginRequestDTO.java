package com.jon.hyf_blog.user.userDTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginRequestDTO {
    private String email;
    private String password;
}
