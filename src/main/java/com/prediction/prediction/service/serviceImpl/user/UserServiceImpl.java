package com.prediction.prediction.service.serviceImpl.user;

import java.util.*;

import com.prediction.util.HashUtil;
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

    @Override
    public User signUp(UserDTO userDto) {

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
            user.setRole(1);
            user.setCredit(5);
            user.setDate(new Date());
            user.setMessage_op(0);
            user.setResult_op(0);
            user.setTeam(userDto.getTeam());
            userRepository.save(user);
            return user;

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
    public List<Object[]> info() {
        return userRepository.findAllUserAndTeam();
    }


}
