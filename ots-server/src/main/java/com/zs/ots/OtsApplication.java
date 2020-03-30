package com.zs.ots;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangshuai
 * 启动类
 */
@MapperScan("com.zs.ots.mapper") // 扫描mapper文件
@SpringBootApplication
public class OtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtsApplication.class, args);
	}

}
