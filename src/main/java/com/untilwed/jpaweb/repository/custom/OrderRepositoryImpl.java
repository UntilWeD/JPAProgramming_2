package com.untilwed.jpaweb.repository.custom;

import com.querydsl.jpa.JPQLQuery;
import com.untilwed.jpaweb.domain.Order;
import com.untilwed.jpaweb.domain.OrderSearch;
import com.untilwed.jpaweb.domain.QMember;
import com.untilwed.jpaweb.domain.QOrder;
import org.apache.catalina.util.StringUtil;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements CustomOrderRepository {
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public OrderRepositoryImpl(Class<?> domainClass) {
        super(domainClass);
    }

    @Override
    public List<Order> search(OrderSearch orderSearch) {

        QOrder order = QOrder.order;
        QMember member = QMember.member;

        JPQLQuery query = from(order);

        if(StringUtils.hasText(orderSearch.getMemberName())){
            query.leftJoin(order.member, member)
                    .where(member.name.contains(orderSearch.getMemberName()));
        }

        if(orderSearch.getOrderStatus() != null){
            query.where(order.status.eq(orderSearch.getOrderStatus()));
        }

        return query.fetch();
    }


}
