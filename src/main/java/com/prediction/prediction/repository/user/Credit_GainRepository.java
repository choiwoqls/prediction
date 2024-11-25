package com.prediction.prediction.repository.user;

import com.prediction.prediction.domain.user.Credit_Gain;
import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.request.user.Credit_GainDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface Credit_GainRepository extends JpaRepository<Credit_Gain, Long> {

    @Query(value = "SELECT c From Credit_Gain c JOIN c.user u WHERE u.id = :id")
    List<Credit_GainDTO> findAllUserAndTeam(Long id);



}
