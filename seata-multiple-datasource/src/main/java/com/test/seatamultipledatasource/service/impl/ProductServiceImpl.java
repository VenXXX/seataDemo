package com.test.seatamultipledatasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.test.seatamultipledatasource.entity.Product;
import com.test.seatamultipledatasource.mapper.ProductMapper;
import com.test.seatamultipledatasource.service.IProductService;
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
@DS("storage-ds")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
