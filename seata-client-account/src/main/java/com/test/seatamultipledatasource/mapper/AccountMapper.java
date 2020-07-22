package com.test.seatamultipledatasource.mapper;

import com.test.seatamultipledatasource.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author renwei
 * @since 2020-07-18
 */
public interface AccountMapper extends BaseMapper<Account> {

    @Update("UPDATE account SET balance = balance - #{price} WHERE id = 1 AND balance >= ${price}")
    int reduceBalance(Double price);
}
