package cn.coderap.edu.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.coderap.edu.user.mapper") //不能少
public class EduUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduUserApplication.class,args);
    }

}
