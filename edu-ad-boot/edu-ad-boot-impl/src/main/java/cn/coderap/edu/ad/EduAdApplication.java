package cn.coderap.edu.ad;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.coderap.edu.ad.mapper") //不能少
public class EduAdApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduAdApplication.class,args);
    }

}
