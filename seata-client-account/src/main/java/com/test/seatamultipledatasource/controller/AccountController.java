package com.test.seatamultipledatasource.controller;


import com.test.seatamultipledatasource.entity.Account;
import com.test.seatamultipledatasource.service.IAccountService;
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
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @RequestMapping("test")
    public void orderTest() throws Exception{
        accountService.reduceBalance(1, 100.0);
    }
}
