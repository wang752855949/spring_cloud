package org.sang.feign.service;
/** 
* @author  作者:柯麒鹰  
* 			 E-mail:wangkeqing@e-eduwisdom.com
* @date 创建时间：2017年12月25日 上午11:50:49  
*/

import java.awt.print.Book;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @FeignClient 注解来指定服务名进而绑定服务，然后再通过SpringMVC中提供的注解来绑定服务提供者提供的接口
 * @author 柯麒鹰
 *
 */
@FeignClient("hello-service")
public interface HelloService1 {
	
	@RequestMapping("hello")
	String hello();
}
