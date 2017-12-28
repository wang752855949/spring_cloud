package org.sang.feign.controller;

import java.net.URLEncoder;

import org.sang.commons.entity.Book;
import org.sang.feign.service.HelloService1;
import org.sang.feign.service.HelloService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
* @author  作者:柯麒鹰  
* 			 E-mail:wangkeqing@e-eduwisdom.com
* @date 创建时间：2017年12月25日 上午11:56:00  
*/

@RestController
public class FeignConsumerController {

	@Autowired
	private HelloService1 service;
	
	@Autowired
	private HelloService2 service2;
	
	@RequestMapping("/hello")
	public String hello() {
		return service.hello();
	}
	
	@RequestMapping("/hello1")
	public String hello1() {
		return service2.hello("張三");
	}
	
	@RequestMapping("/hello2")
	public Book hello2() throws Exception {
		Book book = service2.hello(URLEncoder.encode("三国演义", "UTF-8"), URLEncoder.encode("罗贯中", "UTF-8"), 33);
		System.err.println(book);
		return book;
	}
	
	@RequestMapping("hello3")
	public String hello3() {
		Book book = new Book();
		book.setName("红楼梦")
			.setPrice(40)
			.setAuthor("曹雪芹");
		return service2.hello(book);
	}
}
