package com.untilwed.jpaweb.repository;

import com.untilwed.jpaweb.domain.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface OrderRepositoryV2 extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    //엔티티 그래프 추가
    @EntityGraph(value = "Order.withMember", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Order> findById(Long id);
}
