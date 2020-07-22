package com.test.seatamultipledatasource.service;

import com.test.seatamultipledatasource.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author renwei
 * @since 2020-07-18
 */
public interface IOrdersService extends IService<Orders> {

    void test();
}
