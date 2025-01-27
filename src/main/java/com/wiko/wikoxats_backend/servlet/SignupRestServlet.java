package com.wiko.wikoxats_backend.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wiko.wikoxats_backend.dto.ResponseDto;
import com.wiko.wikoxats_backend.dto.SignupDto;
import com.wiko.wikoxats_backend.service.SignupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/signup")
public class SignupRestServlet extends HttpServlet {
    private SignupService service;

    public SignupRestServlet() {
        service = SignupService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        StringBuilder : 객체를 생성하여 요청 본문 데이터를 읽어들이고 누적 저장하는 데 사용
        StringBuilder stBuilder = new StringBuilder();

        /**
         * BufferedReader를 사용하기 위해 HTTP 요청의 req.getReader()를 호출
         * try-with-resources를 사용하여 BufferedReader가 자동으로 닫히도록 설정
         */
        try (BufferedReader bufferedReader = req.getReader()) {
            String line;

            /**
             * BufferedReader의 readLine() 메서드를 사용하여 HTTP 요청 본문을 한 줄씩 읽어옴
             * 본문 끝에 도달하면 readLine()이 null을 반환
             * StringBuilder에 읽어온 데이터를 한 줄씩 추가
             */
            while ((line = bufferedReader.readLine()) != null) {
                stBuilder.append(line);
            }
        }

        ObjectMapper objMapper = new ObjectMapper();
        SignupDto dto = objMapper.readValue(stBuilder.toString(), SignupDto.class);
        ResponseDto<?> insertedUserDto = service.signup(dto);

        String respJson = objMapper.writeValueAsString(insertedUserDto);
        resp.setStatus(insertedUserDto.getStatus());
        resp.setContentType("application/json");
        resp.getWriter().println(respJson);
    }
}
