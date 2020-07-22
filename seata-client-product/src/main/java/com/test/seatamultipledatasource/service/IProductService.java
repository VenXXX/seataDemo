package com.test.seatamultipledatasource.service;

import com.test.seatamultipledatasource.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author renwei
 * @since 2020-07-18
 */
public interface IProductService extends IService<Product> {

    public void reduceStock(Integer productId, BigDecimal amount) throws Exception;
}
