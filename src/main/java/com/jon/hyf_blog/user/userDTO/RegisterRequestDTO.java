package com.jon.hyf_blog.user.userDTO;

import com.jon.hyf_blog.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class RegisterRequestDTO {
    @NotBlank(message = "userNameMissing")
    private String userName;


    @NotBlank(message = "emailMissing")
    @Email(message = "notAnEmail")
    private String email;

    @NotBlank(message = "passwordMissing")
    private String password;

    @NotNull(message = "roleMissing")
    private Role role;
}
