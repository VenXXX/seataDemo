package com.test.seatamultipledatasource.service.impl;

import com.test.seatamultipledatasource.entity.Account;
import com.test.seatamultipledatasource.mapper.AccountMapper;
import com.test.seatamultipledatasource.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    @Autowired
    private AccountMapper accountMapper;


    @Override
    @Transactional // 开启新事物
    public void reduceBalance(Integer userId, Double price) throws Exception {
        log.info("[reduceBalance] 当前 XID: {}", RootContext.getXID());

        // 检查余额
        checkBalance(userId, price);

        log.info("[reduceBalance] 开始扣减用户 {} 余额", userId);
        // 扣除余额
        int updateCount = accountMapper.reduceBalance(price);
        // 扣除成功
        if (updateCount == 0) {
            log.warn("[reduceBalance] 扣除用户 {} 余额失败", userId);
            throw new Exception("余额不足");
        }
        log.info("[reduceBalance] 扣除用户 {} 余额成功", userId);
    }

    private void checkBalance(Integer userId, Double price) throws Exception {
        log.info("[checkBalance] 检查用户 {} 余额", userId);
        Account account = getById(userId);
        Double balance = account.getBalance();
        if (balance.compareTo(price) < 0) {
            log.warn("[checkBalance] 用户 {} 余额不足，当前余额:{}", userId, balance);
            throw new Exception("余额不足");
        }
    }
}
