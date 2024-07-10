package com.untilwed.jpaweb.repository.custom;

import com.untilwed.jpaweb.domain.Order;
import com.untilwed.jpaweb.domain.OrderSearch;

import java.util.List;

public interface CustomOrderRepository {

    public List<Order> search(OrderSearch orderSearch);
}
