package com.prediction.prediction.security;

import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Transactional
    public UserDetails loadByUserId(Long id){
        System.out.println("loadByUserId");
        User user = this.userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User를 찾을 수 없습니다. id: " + id));
        return UserPrincipal.create(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername + email : " + email);
        User user = this.userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User를 찾을 수 없습니다. email: " + email));
        return UserPrincipal.create(user);
    }
}
