package com.prediction.prediction.service.serviceImpl.user;

import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.request.user.UserDTO;
import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.IncorrectPasswordException;
import com.prediction.prediction.exception.ResourceNotFoundException;
import com.prediction.prediction.security.JwtTokenProvider;
import com.prediction.prediction.security.UserPrincipal;
import com.prediction.prediction.service.user.AuthService;
import com.prediction.prediction.service.user.UserService;
import com.prediction.prediction.util.HashUtil;
import com.prediction.prediction.util.JWTAuthenticationResponse;
import com.prediction.prediction.util.RedisUtil;
import com.prediction.prediction.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserService userService;

    @Autowired
    private final RedisUtil redisUtil;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public JWTAuthenticationResponse login(UserDTO userDto) {
        try {

            String email = userDto.getEmail();
            String password = userDto.getPassword();

            User user1 = userService.getUserByEmail(email);

            if(!HashUtil.isTheSame(password, user1.getPassword())){
                System.out.println("adfgadgagsags");
                throw new IncorrectPasswordException("Incorrect password");
            }
            String jwt = null;
            UserPrincipal userPrincipal = null;
            User user = null;

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
            System.out.println(authRequest);
            System.out.println(userDto.getEmail() + " : " + userDto.getPassword());

            Authentication authentication = authenticationProvider.authenticate(authRequest);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Auth Login");
            jwt = jwtTokenProvider.generateToken(authentication);
            userPrincipal = UserUtils.getLoggedInUser();
            user = userService.getUserById(userPrincipal.getId());
            redisUtil.saveToken(String.valueOf(user.getEmail()), jwt, 60);
            return new JWTAuthenticationResponse(jwt);
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public MessageDto logout(String token) {
        String userEmail = jwtTokenProvider.getUserEmailFromToken(token);
        redisUtil.deleteToken(userEmail);
        SecurityContextHolder.clearContext();
        MessageDto message = new MessageDto();
        message.setMessage("Logout Success");
        return message;
    }
}
