package org.sang.zuul;

import org.sang.zuul.filter.PermisFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
/**
 * @EnableZuulProxy 注解表示开启Zuul的API网关服务功能
 * @author 柯麒鹰
 *
 */
@SpringBootApplication
@EnableZuulProxy
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean
	PermisFilter permisFilter() {
		return new PermisFilter();
	}
}
