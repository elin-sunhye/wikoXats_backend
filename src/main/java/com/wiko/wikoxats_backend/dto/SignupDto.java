package com.wiko.wikoxats_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.wiko.wikoxats_backend.entity.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNum;
    private String rankName;
    private String file;

    public User toUser() {
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .phoneNum(phoneNum)
                .rankName(rankName)
                .file(file)
                .build();
    }
}
