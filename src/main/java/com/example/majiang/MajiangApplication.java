package com.example.majiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.majiang.mapper")  // 上配置@MapperScan，这样就可以省去，单独给每个Mapper上标识@Mapper的麻烦。
public class MajiangApplication {

    public static void main(String[] args) {
        SpringApplication.run(MajiangApplication.class, args);
    }

}
