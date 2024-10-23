package com.prediction.prediction.repository.user;

import com.prediction.prediction.domain.user.Lineup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineupRepository extends JpaRepository<Lineup, Long> {


}
