package com.prediction.prediction.service.serviceImpl.user;

import com.prediction.prediction.domain.user.Role;
import com.prediction.prediction.enumerations.UserRole;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.ResourceNotFoundException;
import com.prediction.prediction.repository.user.RoleRepository;
import com.prediction.prediction.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(UserRole name) {
        try {
            return roleRepository.findByName(name).orElseThrow(()->new ResourceNotFoundException("The Role was not found"));
        }catch (Exception e){
            throw new CustomException(e);
        }
    }
}
