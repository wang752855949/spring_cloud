package org.sang.consumer.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.sang.commons.entity.Book;
import org.sang.consumer.hystrix.BookCommand;
import org.sang.consumer.hystrix.BookCommand2;
import org.sang.consumer.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * @author 作者:柯麒鹰 E-mail:wangkeqing@e-eduwisdom.com
 * @date 创建时间：2017年12月14日 上午11:59:50
 * 
 */
@Controller
public class BookController {

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 1.获取到BookCommand对象之后，我们有两种方式来执行请求，一种是调用execute方法发起一个同步请求，另一种是调用queue方法发起一个异步请求。
	 * 2.同步请求中可以直接返回请求结果。 3.异步请求中我们需要通过get方法来获取请求结果，在调用get方法的时候也可以传入超时时长。
	 * 
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@RequestMapping("/test1")
	@ResponseBody
	public Book test1() throws ExecutionException, InterruptedException {
		BookCommand bookCommand = new BookCommand(
				HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);
		// 同步调用
		 Book book = bookCommand.execute();
		// 异步调用
		/*Future<Book> queue = bookCommand.queue();
		Book book = queue.get();*/
		return book;
	}

	@Autowired
	private BookService service;

	@GetMapping("test3")
	@ResponseBody
	public Book hystrixTest() throws InterruptedException, ExecutionException {
		Future<Book> bookFuture = service.hello2();
		return bookFuture.get();
	}
	
	@GetMapping("test5")
	@ResponseBody
	public Book test5() {
		HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("commandKey");
		HystrixRequestContext.initializeContext();
		BookCommand2 bc1 = new BookCommand2(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(commandKey), restTemplate, 2L);
		Book book1 = bc1.execute();
		//第一次请求结束后将key的缓存清除了。
		HystrixRequestCache.getInstance(commandKey, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(2L));
		BookCommand2 bc2 = new BookCommand2(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(commandKey), restTemplate, 2L);
		Book book2 = bc2.execute();
		BookCommand2 bc3 = new BookCommand2(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(commandKey), restTemplate, 2L);
		Book book3 = bc3.execute();
		System.err.println(book1);
		System.err.println(book2);
		System.err.println(book3);
		return book1;
	}
}
