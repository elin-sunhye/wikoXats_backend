package com.wiko.wikoxats_backend.dao;

import com.wiko.wikoxats_backend.config.DBConnectionMgr;
import com.wiko.wikoxats_backend.entity.User;
import com.wiko.wikoxats_backend.service.FindUserByUsernameService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FindUserByUsernameDao {
    public static FindUserByUsernameDao instance;
    private FindUserByUsernameService service;
    private DBConnectionMgr mgr = null;

    public static FindUserByUsernameDao getInstance() {
        if (instance == null) {
            instance = new FindUserByUsernameDao();
        }

        return instance;
    }

    private FindUserByUsernameDao() {
        service = service.getInstance();
    }

    public User findUsernameByUsername(String username) {
        User foundUser = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = mgr.getConnection();

            String sql = """
                    select
                        user_id,
                        username,
                        name,
                        email,
                        phone_num,
                        rank_name
                    from
                        user_tb
                    where
                        username = ?
                    """;
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.executeUpdate();

            if (rs.next()) {
                foundUser = User.builder()
                        .userId(rs.getInt(1))
                        .username(rs.getString(2))
                        .name(rs.getString(4))
                        .email(rs.getString(5))
                        .phoneNum(rs.getString(6))
                        .rankName(rs.getString(7))
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mgr.freeConnection(con, ps);
        }


        return foundUser;
    }
}
