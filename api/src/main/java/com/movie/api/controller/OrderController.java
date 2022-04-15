package com.movie.api.controller;

import com.movie.api.model.entity.Cart;
import com.movie.api.model.entity.Order;
import com.movie.api.model.vo.OrderVO;
import com.movie.api.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "订单接口")
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("")
    @ApiOperation(value = "创建订单")
    public void save(@RequestBody Cart cart) throws Exception {
        orderService.create(cart);
    }

    @GetMapping("/statistics/{startDate}/{endDate}")
    public Map<String, List<Object>> statistics(@PathVariable("startDate") String startDate,@PathVariable("endDate") String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        Map<String, List<Object>> statistics = orderService.statistics(start, end);
        return statistics;


    }

    @GetMapping("")
    @ApiOperation(value = "查询所有订单")
    public List<OrderVO> findAll() {
        return orderService.findAll();
    }

    @PutMapping("")
    @ApiOperation(value = "查询所有订单")
    public void update(@RequestBody Order order) {
        orderService.update(order);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户订单")
    public List<OrderVO> findByUser(@PathVariable String id) {
        return orderService.findByUser(id);
    }

    @GetMapping("/pay")
    @ApiOperation(value = "支付订单")
    public Order save(String id) throws Exception {
        return orderService.pay(id);
    }

}
