package com.test.seatamultipledatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.test.seatamultipledatasource"})
@MapperScan(basePackages = {"com.test.seatamultipledatasource.mapper"})
public class SeataProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeataProductApplication.class, args);
	}

}
