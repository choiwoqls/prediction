package com.prediction.prediction.service.serviceImpl.user;

import java.util.*;

import com.prediction.prediction.domain.player.Team;
import com.prediction.prediction.domain.user.Role;
import com.prediction.prediction.dto.request.SignUpDTO;
import com.prediction.prediction.dto.response.user.Credit_GainDTO;
import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.enumerations.UserRole;
import com.prediction.prediction.exception.UnauthorizedException;
import com.prediction.prediction.security.JwtTokenProvider;
import com.prediction.prediction.service.service.player.TeamService;
import com.prediction.prediction.service.service.user.Credit_GainService;
import com.prediction.prediction.service.service.user.RoleService;
import com.prediction.prediction.util.HashUtil;
import com.prediction.prediction.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.response.user.UserDTO;
import com.prediction.prediction.exception.BadRequestAlertException;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.NotFoundException;
import com.prediction.prediction.repository.user.UserRepository;
import com.prediction.prediction.service.service.user.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private Credit_GainService creditGainService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public User getUserByEmail(String email) {
        try {
           return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("찾을 수 없는 이메일"));
       }catch (NotFoundException e){
           throw new NotFoundException(e.getMessage());
       }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public UserDTO getUserById(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("찾을 수 없는 유저 ID"));
           return new UserDTO(user);
       }catch (NotFoundException e){
           throw new NotFoundException(e.getMessage());
       }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public UserDTO getUserByToken(HttpServletRequest request) {
         try{
         String jwt = JwtUtil.getJwtFromRequest(request);
            String email = jwtTokenProvider.getUserEmailFromToken(jwt);
            User user = userRepository.findByEmail(email).orElse(null);
            if(user == null){
                throw new NotFoundException("찾을 수 없는 사용자 ID");
            }
            return new UserDTO(user);
        }catch (UnauthorizedException e){
            throw new UnauthorizedException(e.getMessage());
        }catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }catch (Exception e){
            throw new CustomException(e);
        }
    }


    @Override
    public boolean checkEmail(String email){
        try{
            return userRepository.existsByEmail(email);
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    @Transactional
    public MessageDto signUp(SignUpDTO signUpDto) {
        //회원 가입 크레딧 5.
        int credit = 5;
        try {
            System.out.println("one");
            User userEmail = userRepository.findByEmail(signUpDto.getEmail()).orElse(null);
            if (userEmail != null) {
                // 이메일 중복 예외 처리
                throw new BadRequestAlertException("이미 사용중인 이메일");
            }
            System.out.println("two");
            User userNickname = userRepository.findByNickname(signUpDto.getNickname()).orElse(null);
            if (userNickname != null) {
                // 닉네임 중복 예외 처리
                throw new BadRequestAlertException("이미 사용중인 닉네임");
            }
            System.out.println("three");
            // 회원가입 처리
            User user = new User();
            user.setEmail(signUpDto.getEmail());
            user.setNickname(signUpDto.getNickname());
            user.setPassword(HashUtil.hashPassword(signUpDto.getPassword()));
            //role 설정 ..
            Role role = roleService.findByName(UserRole.USER);
            HashSet<Role> roles = new HashSet<>();
            roles.add(role);
            System.out.println("role :" + role.getName());
            user.setRoles(roles);
            user.setCredit(0);
            user.setDate(new Date());
            user.setMessage_op(0);
            user.setResult_op(0);
            Team team = teamService.getTeamById(signUpDto.getTeam_id());
            user.setTeam(team);
            userRepository.save(user);

            this.addCredit(user, 0, credit);

            MessageDto messageDto = new MessageDto();
            messageDto.setType("message");
            messageDto.setData("회원가입 성공");
            return messageDto;

        } catch (BadRequestAlertException e) {
            System.out.println("already using email or nickname");
            throw new BadRequestAlertException(e.getMessage());
        } catch (NotFoundException e) {
            System.out.println("22");
            throw new NotFoundException(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Present Credit");
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            System.out.println("Others");
            throw new CustomException(e);
        }

    }

    @Override
    public List<Credit_GainDTO> info(HttpServletRequest request) {
        System.out.println("info");
        try{
            UserDTO userDto = this.getUserByToken(request);
            System.out.println("user : " + userDto.getId());
            return creditGainService.info(userDto.getId());
        }catch (UnauthorizedException e){
            throw new UnauthorizedException(e.getMessage());
        }catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Transactional
    @Override
    public void addCredit(User user, int type, int credit) {
        try {
            userRepository.addCredit(credit, user.getId());
            creditGainService.presentCredit(user, type, credit);
        }catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Transactional
    @Override
    public void addCredit(List<User> user_list, int type, int credit) {
        try {
            for(User user : user_list){
                userRepository.addCredit(credit, user.getId());
                creditGainService.presentCredit(user, type, credit);
            }
        }catch (Exception e) {
            throw new CustomException(e);
        }
    }



}
