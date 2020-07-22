package com.test.seatamultipledatasource.controller;


import com.test.seatamultipledatasource.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author renwei
 * @since 2020-07-18
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    IProductService productService;

    @RequestMapping("test")
    public boolean productTest(){
        try {
            productService.reduceStock(1, new BigDecimal("1"));
            return true;
        }catch (Exception e){
            log.info("productTest error", e);
            return false;
        }
    }
}
