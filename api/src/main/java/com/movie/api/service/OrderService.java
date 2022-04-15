package com.movie.api.service;

import com.movie.api.model.entity.Cart;
import com.movie.api.model.entity.Order;
import com.movie.api.model.vo.OrderVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderService {

    Order create(Cart cart) throws Exception;

    Order pay(String id) throws Exception;

    void update(Order order);

    List<OrderVO> findAll();

    List<OrderVO> findByUser(String uid);

    Map<String, List<Object>> statistics(Date start, Date end);
}
