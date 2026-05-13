package com.jon.hyf_blog.user.userDTO;

import com.jon.hyf_blog.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserSummaryDTO {
    private Long id;
    private String userName;
    private Role role;
}
