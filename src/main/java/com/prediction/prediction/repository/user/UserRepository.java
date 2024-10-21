package com.prediction.prediction.repository.user;

import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.user.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {




}
