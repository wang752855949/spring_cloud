package org.sang.consumer.service;

import org.sang.commons.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

/** 
* @author  作者:柯麒鹰  
* 			 E-mail:wangkeqing@e-eduwisdom.com
* @date 创建时间：2017年12月14日 上午10:32:20  
*/
@Service
public class HelloService {

	@Autowired
	private RestTemplate restTemplate;
	
	/** 同步请求
	 * @HystrixCommand 
	 * fallbackMethod 注解来指定请求失败时回调的方法
	 * ignoreExceptions 忽略异常
	 * @return
	 */
	@HystrixCommand(fallbackMethod="error",ignoreExceptions=ArithmeticException.class)
	public String hello() {
		ResponseEntity<String> forEntity = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class);
		return forEntity.getBody();
	}
	
	@HystrixCommand
	@CacheResult	//开启缓存;  
	//@CacheKey 指定缓存的key为id,此时只要ID相同就认为是同一个请求
	//如果既使用@CacheResult中的CacheKeyMethod属性来指定key,有使用了@CacheKey注解来指定key,则后者失效
	public Book test6(@CacheKey Integer id,String aa) {
		return restTemplate.getForObject("http://HELLO-SERVICE/getBook5/{1}", Book.class,1L);
	}
	
	//commandKey的值就为缓存的位置，配置了commandKey属性的值，Hystrix才能找到请求命令缓存的位置
	@CacheRemove(commandKey = "test6")
	public Book test7() {
		return restTemplate.getForObject("http://HELLO-SERVICE/getBook5/{1}", Book.class,1L);
	}
	
	public String error() {
		return "error";
	}
	
	//如果我们采用了注解的方式，只需要在服务降级方法中添加一个Throwable类型的参数就能够获取到抛出的异常的类型
	public String error(Throwable throwable) {
		System.err.println(throwable.getMessage());
		return "error";
	}
}
