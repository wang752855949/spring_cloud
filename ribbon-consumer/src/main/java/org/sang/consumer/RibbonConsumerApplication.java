package org.sang.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * @EnableDiscoveryClient 表示该应用是一个Eureka客户端应用，这样该应用就自动具备了发现服务的能力。
 * 
 * @EnableCircuitBreaker 开启断路器功能
 * 
 * @SpringCloudApplication  三合一 @EnableCircuitBreaker @SpringBootApplication @EnableDiscoveryClient
 * @author 柯麒鹰
 *
 */
/*
@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
*/
@SpringCloudApplication
public class RibbonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApplication.class, args);
    }
    
    @LoadBalanced //表示开启客户端负载均衡。
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    /**
     * 断路器Hystrix通过注解实现异步请求
     * 1.配置HystrixCommandAspect的Bean
     * 2.通过在bookService中:AsyncResult来执行调用
     * @return
     */
    @Bean
    public HystrixCommandAspect hystrixCommandAspect() {
        return new HystrixCommandAspect();
    }
    
}
