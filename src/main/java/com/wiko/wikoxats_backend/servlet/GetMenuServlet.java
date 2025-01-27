package com.wiko.wikoxats_backend.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wiko.wikoxats_backend.dto.ResponseDto;
import com.wiko.wikoxats_backend.service.GetMenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/getAllMenu")
public class GetMenuServlet extends HttpServlet {
    private GetMenuService service;

    public GetMenuServlet() {
        service = GetMenuService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseDto<?> responseDto = service.getAllMenu();

        ObjectMapper objMapper = new ObjectMapper();
        String jsonMenu = objMapper.writeValueAsString(responseDto);
        resp.getWriter().write(jsonMenu);
    }
}
