package com.wiko.wikoxats_backend.dao;

import com.wiko.wikoxats_backend.config.DBConnectionMgr;
import com.wiko.wikoxats_backend.entity.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetMenuDao {
    public static GetMenuDao instance;

    private DBConnectionMgr mgr;

    private GetMenuDao() {
        mgr = DBConnectionMgr.getInstance();
    }

    public static GetMenuDao getInstance() {
        if (instance == null) {
            instance = new GetMenuDao();
        }
        return instance;
    }

    public List<Menu> getMenu() {
        List<Menu> allMenu = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {

            con = mgr.getConnection();

            String sql = """
                    select
                        menu_id, parent_seq, menu, url, title, type, level, has_child
                    from
                        menu_tb
                    """;

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            // ResultSet에서 데이터 추출

            System.out.println("rs : " + rs);
            while (rs.next()) {
                allMenu.add(Menu.builder()
                        .menuId(rs.getInt("menu_id"))
                        .parentSeq(rs.getInt("parent_seq"))
                        .menu(rs.getString("menu"))
                        .url(rs.getString("url"))
                        .title(rs.getString("title"))
                        .type(rs.getString("type"))
                        .level(rs.getInt("level"))
                        .hasChild(rs.getBoolean("has_child"))
                        .build());
            }

            System.out.println("addMenu : " + allMenu);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mgr.freeConnection(con, ps, rs);
        }

        return allMenu;
    }
}
