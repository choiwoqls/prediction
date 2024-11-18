package com.prediction.prediction.service.serviceImpl.user;

import java.util.*;

import com.prediction.prediction.domain.player.Team;
import com.prediction.prediction.domain.user.Role;
import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.enumerations.UserRole;
import com.prediction.prediction.service.player.TeamService;
import com.prediction.prediction.service.user.RoleService;
import com.prediction.prediction.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.request.user.UserDTO;
import com.prediction.prediction.exception.BadRequestAlertException;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.NotFoundException;
import com.prediction.prediction.repository.user.UserRepository;
import com.prediction.prediction.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TeamService teamService;

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
    public User getUserById(Long id) {
        try {
           return userRepository.findById(id).orElseThrow(() -> new NotFoundException("찾을 수 없는 유저 ID"));
       }catch (NotFoundException e){
           throw new NotFoundException(e.getMessage());
       }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public boolean checkEmail(String email){
        try{
            return userRepository.findByEmail(email).isEmpty();
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public MessageDto signUp(UserDTO userDto) {

        try {
            System.out.println("one");
            User userEmail = userRepository.findByEmail(userDto.getEmail()).orElse(null);
            if (userEmail != null) {
                // 이메일 중복 예외 처리
                throw new BadRequestAlertException("이미 사용중인 이메일");
            }
            System.out.println("two");
            User userNickname = userRepository.findByNickname(userDto.getNickname()).orElse(null);
            if (userNickname != null) {
                // 닉네임 중복 예외 처리
                throw new BadRequestAlertException("이미 사용중인 닉네임");
            }
            System.out.println("three");
            // 회원가입 처리
            User user = new User();
            user.setEmail(userDto.getEmail());
            user.setNickname(userDto.getNickname());
            user.setPassword(HashUtil.hashPassword(userDto.getPassword()));
            //role 설정 ..
            Role role = roleService.findByName(UserRole.USER);
            HashSet<Role> roles = new HashSet<>();
            roles.add(role);
            System.out.println("role :" + role.getName());
            user.setRoles(roles);
            user.setCredit(5);
            user.setDate(new Date());
            user.setMessage_op(0);
            user.setResult_op(0);
            Team team = teamService.getTeamById(userDto.getTeam_id());
            user.setTeam(team);
            userRepository.save(user);
            MessageDto messageDto = new MessageDto();
            messageDto.setMessage("회원가입 성공");
            return messageDto;

        } catch (BadRequestAlertException e) {
            System.out.println("11");
            throw new BadRequestAlertException(e.getMessage());
        } catch (NotFoundException e) {
            System.out.println("22");
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            System.out.println("33");
            throw new CustomException(e);
        }

    }



    @Override
    public List<Object> info() {
        return userRepository.findAllUserAndTeam();
    }


}
