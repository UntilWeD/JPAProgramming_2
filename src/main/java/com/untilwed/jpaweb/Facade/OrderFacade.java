package com.untilwed.jpaweb.Facade;

import com.untilwed.jpaweb.domain.Order;
import com.untilwed.jpaweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderFacade {

    @Autowired
    OrderService orderService;

    public Order findOrder(Long id){
        Order order = orderService.findOrder(id);
        //프레젠테이션 계층이 필요한 프록시 객체를 강제로 초기화 한다.
        order.getMember().getName();
        return order;
    }
}
