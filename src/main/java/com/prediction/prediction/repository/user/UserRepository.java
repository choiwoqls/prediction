package com.prediction.prediction.repository.user;

import com.prediction.prediction.domain.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM user   WHERE id = :id", nativeQuery = true)
    User findAllUserAndTeam(Long id);

}
