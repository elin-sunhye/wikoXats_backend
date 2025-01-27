//package com.wiko.wikoxats_backend.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.korit.servlet_study.dao.UserDao;
//import com.korit.servlet_study.dto.ResponseDto;
//import com.korit.servlet_study.entity.User;
//import com.korit.servlet_study.security.annotation.JwtValid;
//import com.korit.servlet_study.security.jwt.JwtProvider;
//import io.jsonwebtoken.Claims;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.lang.reflect.Method;
//
/// /@WebFilter({"/api/user/*", "*"})
//public class AuthenticationFilter implements Filter {
//    private JwtProvider jwtProvider;
//    private UserDao userDao;
//
//    public AuthenticationFilter() {
//        jwtProvider = JwtProvider.getInstance();
//        userDao = UserDao.getInstance();
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////        다운 캐스팅
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        try {
//            System.out.println(isJwtTokenValid(request));
////            커스텀 어노테이션
//            if (isJwtTokenValid(request)) {
//
////                있는지 없는지만 확인
//                String bearerToken = request.getHeader("Authorization");
//                if (bearerToken == null) {
//                    setUnAuthenticatedResponse(response);
//                    return;
//                }
//
////                claims 유효 검증
//                Claims claims = jwtProvider.parseToken(bearerToken);
//                if (claims == null) {
//                    setUnAuthenticatedResponse(response);
//                    return;
//                }
//
////                 userId 유효 검증 : 탈퇴한 회원 토큰을 가지고 있는 경우 토큰 ok 뜨면 해당 토큰을 가지고 있는 userId가 있는지 검사
//                int userId = Integer.parseInt(claims.get("userId").toString());
//                User foundUser = userDao.findById(userId);
//                if (foundUser == null) {
//                    setUnAuthenticatedResponse(response);
//                    return;
//                }
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
////        검증 완료
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    //        인증이 필요한 요청인지 아닌지 검증
//    private boolean isJwtTokenValid(HttpServletRequest request) throws ClassNotFoundException {
//        String servletPath = request.getHttpServletMapping().getServletName(); // 요청이 들어온 경로(패키지와 모든 경로를 포함한 것) ServletName == class 이름
//        String method = request.getMethod(); // 요청이 들어온 메서드
//
//        Class<?> servletClass = Class.forName(servletPath);
//        Method foundMethod = getMappedMethod(servletClass, method);
//        return foundMethod != null;
//    }
//
//    private Method getMappedMethod(Class<?> clazz, String methodName) {
//        for (Method method : clazz.getDeclaredMethods()) {
////            method 이름 같은지(get = get, post = post),  커스텀 JwtValid.class 어노테이션 적용 여부 확인
//            if (method.getName().toLowerCase().endsWith(methodName.toLowerCase()) && method.isAnnotationPresent(JwtValid.class)) {
//                return method;
//            }
//        }
//        return null;
//    }
//
//    //    중복 코드 함수화
//    private void setUnAuthenticatedResponse(HttpServletResponse response) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ResponseDto<String> responseDto = ResponseDto.forbidden("검증 할 수 없는 Access Token 입니다.");
//        response.setStatus(responseDto.getStatus());
//        response.setContentType("application/json");
//        response.getWriter().println(objectMapper.writeValueAsString(responseDto));
//    }
//
//}
