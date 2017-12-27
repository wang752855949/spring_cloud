package org.sang.consumer.hystrix;

import org.sang.commons.entity.Book;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;

/**
 * @author 作者:柯麒鹰 E-mail:wangkeqing@e-eduwisdom.com
 * @date 创建时间：2017年12月14日 上午11:50:34
 * 
 * 自定义HystrixCommand
 * 在BookCommand中注入RestTemplate，然后重写两个方法：
 * 一个是getFallback，这个方法将在服务调用失败时回调；
 * 另一个是run方法，执行请求时调用。构造方法的第一个参数主要用来保存一些分组信息。
 */
public class BookCommand extends HystrixCommand<Book> {

	private RestTemplate restTemplate;

	public BookCommand(Setter setter, RestTemplate restTemplate) {
		super(setter);
		this.restTemplate = restTemplate;
	}

	//这个方法将在服务调用失败时回调
	@Override
	protected Book getFallback() {
		Throwable executionException = getExecutionException();
		System.err.println(executionException.getMessage());
		return new Book("宋诗选注", 88, "钱钟书", "三联书店");
	}

	//执行请求时调用
	@Override
	protected Book run() throws Exception {
//		int a = 1/0;
		return restTemplate.getForObject("http://HELLO-SERVICE/book", Book.class);
	}

}
