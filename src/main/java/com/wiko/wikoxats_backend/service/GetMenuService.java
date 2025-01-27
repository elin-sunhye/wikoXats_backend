package com.wiko.wikoxats_backend.service;

import com.wiko.wikoxats_backend.dao.GetMenuDao;
import com.wiko.wikoxats_backend.dto.ResponseDto;
import com.wiko.wikoxats_backend.entity.Menu;

import java.util.List;

public class GetMenuService {
    private static GetMenuService instance;
    private GetMenuDao dao;

    private GetMenuService() {
        dao = GetMenuDao.getInstance();
    }

    public static GetMenuService getInstance() {
        if (instance == null) {
            instance = new GetMenuService();
        }

        return instance;
    }

    public ResponseDto<?> getAllMenu() {
        List<Menu> allMenu = dao.getMenu();

        System.out.println("allMenu" + allMenu);

        if (allMenu == null || allMenu.size() == 0) {
            return ResponseDto.fail("Fail to get all menu");
        }

        return ResponseDto.success(allMenu);
    }
}
