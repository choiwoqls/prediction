package com.prediction.prediction.repository.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.prediction.prediction.domain.jwt.Refresh;

public interface RefreshRepository extends JpaRepository<Refresh, Long> {

    Boolean existsByRefresh(String Refresh);

    @Transactional
    void deleteByRefresh(String Refresh);
}
