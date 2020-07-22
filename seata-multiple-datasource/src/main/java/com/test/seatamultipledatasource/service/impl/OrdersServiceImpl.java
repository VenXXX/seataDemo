package com.test.seatamultipledatasource.service.impl;

import com.test.seatamultipledatasource.entity.Account;
import com.test.seatamultipledatasource.entity.Orders;
import com.test.seatamultipledatasource.mapper.OrdersMapper;
import com.test.seatamultipledatasource.service.IAccountService;
import com.test.seatamultipledatasource.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author renwei
 * @since 2020-07-18
 */
@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {
    @Autowired
    private IAccountService accountService;

    @Override
    @GlobalTransactional
    public void test() {
        Orders order = new Orders();
        order.setAmount(new BigDecimal("111"));
        order.setProductId(1);
        order.setUserId(1001);
        save(order);
        log.info("插入order表");
        Account account = new Account();
        account.setBalance(123.11);
        accountService.save(account);
        log.info("插入account表");
        throw new RuntimeException("故意的");
    }
}
