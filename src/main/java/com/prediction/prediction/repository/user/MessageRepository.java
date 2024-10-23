package com.prediction.prediction.repository.user;

import com.prediction.prediction.domain.user.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {


}
