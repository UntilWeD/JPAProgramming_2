package com.untilwed.jpaweb.repository;

import com.untilwed.jpaweb.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepositoryV2 extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

}
