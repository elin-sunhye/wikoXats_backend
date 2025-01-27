package com.wiko.wikoxats_backend.service;

import com.wiko.wikoxats_backend.dao.SignupDao;
import com.wiko.wikoxats_backend.dto.ResponseDto;
import com.wiko.wikoxats_backend.dto.SignupDto;
import com.wiko.wikoxats_backend.entity.User;

public class SignupService {
    private static SignupService instance;
    private SignupDao dao;

    private SignupService() {
        dao = SignupDao.getInstance();
    }

    public static SignupService getInstance() {
        if (instance == null) {
            instance = new SignupService();
        }

        return instance;
    }

    public ResponseDto<?> signup(SignupDto dto) {
        System.out.println(dto);
        User user = dto.toUser();
        User insertedUser = dao.signup(user);

        if (insertedUser == null) {
            return ResponseDto.fail("Fail to add user");
        }

        return ResponseDto.success("Success to add user");
    }
}
