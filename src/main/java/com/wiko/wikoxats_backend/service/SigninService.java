package com.wiko.wikoxats_backend.service;

import com.wiko.wikoxats_backend.dao.FindUserByUsernameDao;

public class SigninService {
    private static SigninService instance;
    private FindUserByUsernameDao dao;

    public SigninService () {
        dao = FindUserByUsernameDao.getInstance();
    }

    public static SigninService getInstance() {
        if(instance == null) {
            instance = new SigninService();
        }

        return instance;
    }
}
