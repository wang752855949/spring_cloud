package org.sang.consumer.service;


import java.util.concurrent.Future;

import org.sang.commons.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;


/** 
* @author  作者:柯麒鹰  
* 			 E-mail:wangkeqing@e-eduwisdom.com
* @date 创建时间：2017年12月14日 下午1:59:05  
*/
@Service
public class BookService {

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 断路器Hystrix异步请求
	 * @return
	 */
	@HystrixCommand
	public Future<Book> hello2() {
		return new AsyncResult<Book>() {

			@Override
			public Book invoke() {
				return restTemplate.getForObject("http://HELLO-SERVICE/book", Book.class);
			}
			
		};
	}
}
