package com.untilwed.jpaweb.service;

import com.untilwed.jpaweb.domain.*;
import com.untilwed.jpaweb.domain.item.Item;
import com.untilwed.jpaweb.repository.MemberRepository;
import com.untilwed.jpaweb.repository.MemberRepositoryV2;
import com.untilwed.jpaweb.repository.OrderRepository;
import com.untilwed.jpaweb.repository.OrderRepositoryV2;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    MemberRepositoryV2 memberRepository;

    @Autowired
    OrderRepositoryV2 orderRepository;

    @Autowired
    ItemService itemService;

    /** 주문 */
    public Long order(Long memberId, Long itemId, int count){

        //엔티티 조회
        Optional<Member> member = memberRepository.findById(memberId);
        Item item = itemService.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery(member.get().getAddress());
        //주문 상품 생성
        OrderItem orderItem =
                OrderItem.createOrderItem(item, item.getPrice(), count);
        //주문 생성
        Order order = Order.createOrder(member.get(), delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    /** 주문 취소*/
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Optional<Order> order = orderRepository.findById(orderId);
        //주문 취소
        order.get().cancel();
    }

    /** 주문 검색*/
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return orderRepository.findAll(orderSearch);
//    }
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch.toSpecification());
    }
}
