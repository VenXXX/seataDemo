package com.test.seatamultipledatasource.controller;


import com.test.seatamultipledatasource.entity.Account;
import com.test.seatamultipledatasource.entity.Orders;
import com.test.seatamultipledatasource.service.IAccountService;
import com.test.seatamultipledatasource.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private IAccountService accountService;

    @RequestMapping("test")
    public void orderTest(){
        Orders order = ordersService.getById(1);
        System.out.println("amount=====" + order.getAmount());
        Account account = accountService.getById(1);
        System.out.println("account=====" + account.getBalance());
        //测试事务
        ordersService.test();
    }
}
