package com.test.seatamultipledatasource.controller;


import com.test.seatamultipledatasource.entity.Orders;
import com.test.seatamultipledatasource.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author renwei
 * @since 2020-07-18
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    @RequestMapping("test")
    public void orderTest() throws Exception{
        ordersService.createOrder(1, 1, new BigDecimal("100"));
    }
}
