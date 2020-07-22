package com.test.seatamultipledatasource.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.test.seatamultipledatasource.entity.Orders;
import com.test.seatamultipledatasource.mapper.OrdersMapper;
import com.test.seatamultipledatasource.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import io.seata.integration.http.DefaultHttpExecutor;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Override
    @GlobalTransactional
    public Integer createOrder(Integer userId, Integer productId, BigDecimal price) throws Exception {
        BigDecimal amount = BigDecimal.ONE; // 购买数量，暂时设置为 1。

        log.info("[createOrder] 当前 XID: {}", RootContext.getXID());

        // 扣减库存
        this.reduceStock(productId, amount);

        // 扣减余额
        this.reduceBalance(userId, price);

        // 保存订单
        Orders order = new Orders();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setAmount(amount.add(price));
        save(order);
        log.info("[createOrder] 保存订单: {}", order.getId());

        // 返回订单编号
        return order.getId();
    }

    private void reduceStock(Integer productId, BigDecimal amount) throws IOException {
        // 参数拼接
        JSONObject params = new JSONObject().fluentPut("productId", String.valueOf(productId))
                .fluentPut("amount", String.valueOf(amount));
        // 执行调用
        HttpResponse response = DefaultHttpExecutor.getInstance().executePost("http://127.0.0.1:8082", "/product/test",
                params, HttpResponse.class);
        // 解析结果
        Boolean success = Boolean.valueOf(EntityUtils.toString(response.getEntity()));
        if (!success) {
            throw new RuntimeException("扣除库存失败");
        }
    }

    private void reduceBalance(Integer userId, BigDecimal price) throws IOException {
        // 参数拼接
        JSONObject params = new JSONObject().fluentPut("userId", String.valueOf(userId))
                .fluentPut("price", String.valueOf(price));
        // 执行调用
        HttpResponse response = DefaultHttpExecutor.getInstance().executePost("http://127.0.0.1:8084", "/account/test",
                params, HttpResponse.class);
        // 解析结果
        Boolean success = Boolean.valueOf(EntityUtils.toString(response.getEntity()));
        if (!success) {
            throw new RuntimeException("扣除余额失败");
        }
    }
}
