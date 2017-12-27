package org.sang.consumer.hystrix;

import org.sang.commons.entity.Book;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

/** 
* @author  作者:柯麒鹰  
* 			 E-mail:wangkeqing@e-eduwisdom.com
* @date 创建时间：2017年12月15日 上午11:56:48  
*/
public class BookCommand2 extends HystrixCommand<Book> {
	
	private RestTemplate restTemplate;
	private Long id;

	public BookCommand2(Setter setter,RestTemplate restTemplate,Long id) {
		super(setter);
		this.restTemplate = restTemplate;
		this.id = id;
	}

	//@CacheResult(cacheKeyMethod = "getCacheKey") //制定放回缓存key的方法,注意返回的key要是String类型的
	@CacheResult	//表示该方法开起缓存,默认情况下方法的所有参数都将作为缓存的key
	@Override
	protected Book run() throws Exception {
		return restTemplate.getForObject("http://HELLO-SERVICE/getBook5/{1}", Book.class,id);
	}
	
	@Override
	protected Book getFallback() {
		
		Throwable executionException = getExecutionException();
		System.err.println(executionException.getMessage());
		return new Book("宋诗选注", 88, "钱钟书", "三联书店");
	}
	
	//如果我们使用了自定义Hystrix请求命令的方式来使用Hystrix，那么我们只需要重写getCacheKey方法即可实现请求缓存
	@Override
	protected String getCacheKey() {
		return String.valueOf(id);
	}
/**
 * 注解开启缓存
 * @CacheResult 表示给该方法开启缓存,默认情况下方法的所有参数都将作为缓存的key
 * @CacheKey 
 * @CacheRemove
 */
	
	
}
