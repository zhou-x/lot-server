package com.lot;

import cn.hutool.core.util.NetUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;


/**
 * @author zel
 */
@EnableJpaAuditing
@SpringBootApplication
@MapperScan(basePackages = "com.lot.mapper")
public class LotteryApplication {

    private static final int port = 9090;

    public static void main(String[] args) {
        System.out.println(port);
        if (!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port);
            System.exit(1);
        }
        new SpringApplicationBuilder(LotteryApplication.class).properties("server.port=" + port).run(args);
    }
}
