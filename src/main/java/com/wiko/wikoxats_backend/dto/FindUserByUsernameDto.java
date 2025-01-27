package com.wiko.wikoxats_backend.dto;

import com.wiko.wikoxats_backend.entity.User;

public class FindUserByUsernameDto {
    private String username;

    public User toUser() {
        return User.builder()
                .username(username)
                .build();
    }
}
