package com.test.seatamultipledatasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.test.seatamultipledatasource.entity.Account;
import com.test.seatamultipledatasource.mapper.AccountMapper;
import com.test.seatamultipledatasource.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author renwei
 * @since 2020-07-18
 */
@Service
@DS("account-ds")
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
