package org.sang.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * 通过添加@EnableDiscoveryClient注解来激活Eureka中的DiscoveryClient实现
 * （因为我们在HelloController中注入了DiscoveryClient）
 * @author ThinkPad
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}
}
