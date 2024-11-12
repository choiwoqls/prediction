package com.prediction.prediction.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.NotFoundException;
import com.prediction.prediction.exception.UnauthorizedException;
import com.prediction.prediction.util.ApiResponse;
import com.prediction.prediction.util.JwtUtil;
import com.prediction.prediction.util.RedisUtil;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private RedisUtil redisUtil;


    //인증이 필요한 작업이 오면 JWT 토큰 검증
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //보통 cors 요청 검사를 하기 위해 OPTIONS로 요청 할 수 있다.
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            //JWT 토큰 인증
            try {
                String jwt = JwtUtil.getJwtFromRequest(request);
                if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                    //JWT 토큰이 있고 유효하면 ContextHolder에 객체 저장. (성공)
                    String userEmail = jwtTokenProvider.getUserEmailFromToken(jwt);
                    //redis 토큰 일치 확인. 일치하지 않으면 예외 던짐.
                    redisUtil.matchedToken(userEmail, jwt);
                        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }else{
                    throw new UnauthorizedException("Invalid Token");
                }
            }catch (UnauthorizedException e){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json"); // 응답 타입 설정

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper
                        .writeValueAsString(ApiResponse.error(e.getMessage(),HttpStatus.UNAUTHORIZED)
                        .toResponseEntity());
                response.getWriter().write(json);
                return;
            } catch (Exception e) {
                logger.error("Could not set user authentication in security context", e);
            }
            filterChain.doFilter(request, response);
        }
    }
//
//    private String getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }


}
