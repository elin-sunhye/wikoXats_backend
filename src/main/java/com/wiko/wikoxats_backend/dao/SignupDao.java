package com.wiko.wikoxats_backend.dao;

import com.wiko.wikoxats_backend.config.DBConnectionMgr;
import com.wiko.wikoxats_backend.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignupDao {
    public static SignupDao instance;

    private DBConnectionMgr mgr;

    private SignupDao() {
        mgr = DBConnectionMgr.getInstance();
    }

    public static SignupDao getInstance() {
        if (instance == null) {
            instance = new SignupDao();
        }

        return instance;
    }

    public User signup(User user) {
        System.out.println(user);
        User insertedUser = null;

        Connection con = null;
        PreparedStatement ps = null;

        try {

            System.out.println(user.getUsername());

            System.out.println(mgr.getConnection());
            con = mgr.getConnection();


            String sql = """
                        insert into user_tb
                        values
                            (default, ?, ?, ?, ?, ?, ?, ?)
                    """;
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhoneNum());
            ps.setString(6, user.getRankName());
            ps.setString(7, user.getFile());
            ps.executeUpdate();


            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insertedUser = User.builder()
                        .userId(rs.getInt(1))
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .name(user.getName())
                        .email(user.getEmail())
                        .phoneNum(user.getPhoneNum())
                        .rankName(user.getRankName())
                        .file(user.getFile())
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mgr.freeConnection(con, ps);
        }

        System.out.println(insertedUser);
        return insertedUser;
    }
}
