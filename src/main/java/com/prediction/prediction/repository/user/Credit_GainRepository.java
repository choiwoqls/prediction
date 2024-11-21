package com.prediction.prediction.repository.user;

import com.prediction.prediction.domain.user.Credit_Gain;
import com.prediction.prediction.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Credit_GainRepository extends JpaRepository<Credit_Gain, Long> {

    @Query(value = "SELECT * FROM credit_gain WHERE user_id = :id", nativeQuery = true)
    Credit_Gain findAllUserAndTeam(Long id);



}
