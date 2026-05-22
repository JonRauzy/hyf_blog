package com.jon.hyf_blog.user.userExeption;

public class UserExist extends RuntimeException {
    public UserExist(String message) {
        super("User exist");
    }
}
