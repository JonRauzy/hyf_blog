package com.jon.hyf_blog.user.userDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

    private String token;   //access token
//    private String refreshToken;    //refresh token may be implement
}