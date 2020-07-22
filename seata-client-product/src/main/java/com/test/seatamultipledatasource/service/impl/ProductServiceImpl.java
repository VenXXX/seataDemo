package com.test.seatamultipledatasource.service.impl;

import com.test.seatamultipledatasource.entity.Product;
import com.test.seatamultipledatasource.mapper.ProductMapper;
import com.test.seatamultipledatasource.service.IProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional // 开启事物
    public void reduceStock(Integer productId, BigDecimal amount) throws Exception {
        log.info("[reduceStock] 当前 XID: {}", RootContext.getXID());

        // 检查库存
        log.info("[checkStock] 检查 {} 库存", productId);
        Product product = getById(productId);
        Integer stock = product.getStock();
        if (new BigDecimal(stock).compareTo(amount) < 0) {
            log.warn("[checkStock] {} 库存不足，当前库存: {}", productId, stock);
            throw new Exception("库存不足");
        }

        log.info("[reduceStock] 开始扣减 {} 库存", productId);
        // 扣减库存

        int updateCount = productMapper.reduceStock(productId, amount);

        // 扣除失败
        if (updateCount == 0) {
            log.warn("[reduceStock] 扣除 {} 库存失败", productId);
            throw new Exception("库存不足");
        }
        // 扣除成功
        log.info("[reduceStock] 扣除 {} 库存成功", productId);
    }

}
