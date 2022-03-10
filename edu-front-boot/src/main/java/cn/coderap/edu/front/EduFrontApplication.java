package cn.coderap.edu.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("cn.coderap.edu")
public class EduFrontApplication {

    public static void main(String[] args){
        SpringApplication.run(EduFrontApplication.class,args);
    }
}
