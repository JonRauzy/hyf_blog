package com.jon.hyf_blog.user.dto;

import com.jon.hyf_blog.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UpdateUserRequestDTO {
    @NotBlank
    private String userName;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private Role role;
}
