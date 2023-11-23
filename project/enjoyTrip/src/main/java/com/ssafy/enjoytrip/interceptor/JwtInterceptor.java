package com.ssafy.enjoytrip.interceptor;

import com.ssafy.enjoytrip.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    final String ADMIN = "admin";
    final String USER = "user";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //GET, OPTIONS 요청의 경우 모두 허용
        String method = request.getMethod();
        if(method.equals("OPTIONS")){
            return true;
        }
        if(method.equals("GET") && !(request.getRequestURI().equals("/members") || request.getRequestURI().equals("/members/mypage"))){
            return true;
        }

        final String token = request.getHeader("token");

        if (token == null || !token.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return false;
        }
        try {
            boolean result = true;
            String jwtToken = token.substring(7); // "Bearer " 이후의 토큰 부분 추출
            Jws<Claims> parsedToken = JWTUtil.validateToken(jwtToken);
            // 토큰 검증 로직 구현
            String role = parsedToken.getBody().get("role").toString();
            JWTUtil.userNo = Integer.parseInt(parsedToken.getBody().get("userNo").toString());
            boolean isAdmin = role.equals(ADMIN);

            switch (method) {
                case "GET":
                    if (request.getRequestURI().equals("/members") && !isAdmin) {
                        result = false;
                    }
                    break;
                case "POST":
                case "PUT":
                case "DELETE":
                    if (request.getRequestURI().equals("/notices")) {
                        if (!isAdmin) {
                            result = false;
                        }
                    }
                    break;
            }

            // 검증에 성공한 경우 계속 진행
            return result;

        } catch (Exception e) {
            log.debug("만료된 토큰이거나 유효하지 않은 토큰, 에러내용 : {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired");
            return false;
        }
    }
}
