package cn.coderap.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("cn.coderap.edu")
public class EduBossApplication {

    public static void main(String[] args){
        SpringApplication.run(EduBossApplication.class,args);
    }
}
