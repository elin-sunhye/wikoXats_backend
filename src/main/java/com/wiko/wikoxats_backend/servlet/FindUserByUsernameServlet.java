package com.wiko.wikoxats_backend.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wiko.wikoxats_backend.dto.FindUserByUsernameDto;
import com.wiko.wikoxats_backend.dto.ResponseDto;
import com.wiko.wikoxats_backend.service.FindUserByUsernameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/foundUserByUsername")
public class FindUserByUsernameServlet extends HttpServlet {
    private FindUserByUsernameService service;

    public FindUserByUsernameServlet() {
        service = FindUserByUsernameService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder stBuilder = new StringBuilder();

        try (BufferedReader buffered = req.getReader()) {
            String line;

//            무조건 한줄
            if ((line = buffered.readLine()) != null) {
                stBuilder.append(line);
            }

        }

        ObjectMapper objMapper = new ObjectMapper();
        FindUserByUsernameDto dto = objMapper.readValue(stBuilder.toString(), FindUserByUsernameDto.class);
        ResponseDto<?> foundUser = service.findUserByUsername(dto);

        String respJson = objMapper.writeValueAsString(foundUser);
        resp.setStatus(foundUser.getStatus());
        resp.setContentType("application/json");
        resp.getWriter().println(respJson);
    }
}
