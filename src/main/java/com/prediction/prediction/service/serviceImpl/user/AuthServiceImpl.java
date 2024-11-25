package com.prediction.prediction.service.serviceImpl.user;

import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.request.auth.CodeDTO;
import com.prediction.prediction.dto.request.auth.LoginDTO;
import com.prediction.prediction.dto.request.auth.rePasswordDTO;
import com.prediction.prediction.dto.request.user.UserDTO;
import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.IncorrectPasswordException;
import com.prediction.prediction.exception.NotFoundException;
import com.prediction.prediction.exception.UnauthorizedException;
import com.prediction.prediction.repository.user.UserRepository;
import com.prediction.prediction.security.JwtTokenProvider;
import com.prediction.prediction.security.UserPrincipal;
import com.prediction.prediction.service.service.user.AuthService;
import com.prediction.prediction.service.service.user.UserService;
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
    private final UserRepository userRepository;

    @Autowired
    private final RedisUtil redisUtil;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public JWTAuthenticationResponse login(LoginDTO loginDTO) {
        try {

            String email = loginDTO.getEmail();
            String password = loginDTO.getPassword();

            User user1 = userService.getUserByEmail(email);

            if(!HashUtil.isTheSame(password, user1.getPassword())){
                throw new IncorrectPasswordException("일치하지 않는 비밀번호");
            }
            String jwt = null;
            UserPrincipal userPrincipal = null;
            UserDTO userDto = null;

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
            System.out.println(authRequest);
            System.out.println(loginDTO.getEmail() + " : " + loginDTO.getPassword());

            Authentication authentication = authenticationProvider.authenticate(authRequest);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Auth Login");
            jwt = jwtTokenProvider.generateToken(authentication);
            userPrincipal = UserUtils.getLoggedInUser();
            userDto = userService.getUserById(userPrincipal.getId());
            redisUtil.saveToken(String.valueOf(userDto.getEmail()), jwt, 60);
            return new JWTAuthenticationResponse(jwt);
        }catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }catch (IncorrectPasswordException e){
            throw new IncorrectPasswordException(e.getMessage());
        }catch (Exception e){
            System.out.println("error : " + e.getClass().toString());
            throw new CustomException(e);
        }
    }

    @Override
    public MessageDto logout(String token) {
        try {
            String userEmail = jwtTokenProvider.getUserEmailFromToken(token);
            System.out.println("userEmail : "+userEmail);

            redisUtil.deleteToken(userEmail);

            MessageDto message = new MessageDto();
            SecurityContextHolder.clearContext();
            message.setType("message");
            message.setData("Logout Success");
            return message;
        }catch (UnauthorizedException e){
            throw new UnauthorizedException(e.getMessage());
        }
    }

    @Override
    public MessageDto checkCode(CodeDTO codeDto) {
        try {
            MessageDto message = new MessageDto();
            redisUtil.matchedToken(codeDto.getEmail(), codeDto.getCode());
            redisUtil.deleteToken(codeDto.getEmail());
            message.setType("message");
            message.setData("이메일 인증 성공.");
            return message;
        }catch (UnauthorizedException e){
            throw new UnauthorizedException("일치하지 않는 코드.");
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public MessageDto resetPassword(rePasswordDTO rePasswordDto) {
        try {
            MessageDto message = new MessageDto();
            User user = userService.getUserByEmail(rePasswordDto.getEmail());
            if(!rePasswordDto.getPassword().equals(rePasswordDto.getRePassword())){
                throw new IncorrectPasswordException("일치하지 않는 비밀번호");
            }
            user.setPassword(HashUtil.hashPassword(rePasswordDto.getPassword()));
            userRepository.save(user);
            message.setType("message");
            message.setData("비밀번호 변경 성공.");
            return message;
        }catch (IncorrectPasswordException e){
            throw new IncorrectPasswordException(e.getMessage());
        }catch (NotFoundException e){
           throw new NotFoundException(e.getMessage());
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

}
