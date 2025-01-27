package com.wiko.wikoxats_backend.service;

import com.wiko.wikoxats_backend.dao.FindUserByUsernameDao;
import com.wiko.wikoxats_backend.dto.FindUserByUsernameDto;
import com.wiko.wikoxats_backend.dto.ResponseDto;
import com.wiko.wikoxats_backend.entity.User;

public class FindUserByUsernameService {
    public static FindUserByUsernameService instance;
    private FindUserByUsernameDao dao;

    public FindUserByUsernameService() {
        dao = FindUserByUsernameDao.getInstance();
    }

    public static FindUserByUsernameService getInstance() {
        if (instance == null) {
            instance = new FindUserByUsernameService();
        }

        return instance;
    }

    public ResponseDto<?> findUserByUsername(FindUserByUsernameDto dto) {
        User user = dto.toUser();
        User foundUser = dao.findUsernameByUsername(user.getUsername());

        if (foundUser == null) {
            return ResponseDto.fail("Fail to found user");
        }
        return ResponseDto.success(foundUser);
    }
}
