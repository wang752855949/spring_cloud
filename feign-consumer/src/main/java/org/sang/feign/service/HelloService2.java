package org.sang.feign.service;

import org.sang.feign.hystrix.HelloServiceFallback;
import org.sang.service.HelloService;
import org.springframework.cloud.netflix.feign.FeignClient;

/** 
* @author  作者:柯麒鹰  
* 			 E-mail:wangkeqing@e-eduwisdom.com
* @date 创建时间：2017年12月25日 下午4:26:59  
*/
@FeignClient(value="hello-service",fallback=HelloServiceFallback.class)
public interface HelloService2 extends HelloService {

}
