package com.untilwed.jpaweb.domain.listener;


import com.untilwed.jpaweb.domain.Order;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 특정 엔티티 실행 후 로그 시 Listener로 JPA이벤트시점마다 출력함.
 */
@Slf4j
public class OrderListener {

    @PrePersist // Persist 전
    private void prePersist(Order order){
        log.info("[OrderListener] PrePersist = {} ", order);
    }

    @PostPersist // Persist 후
    private void postPersist(Order order){
        log.info("[OrderListener] PostPersist = {} ", order);
    }
}
