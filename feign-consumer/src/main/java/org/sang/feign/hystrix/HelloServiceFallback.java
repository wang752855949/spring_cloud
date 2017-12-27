package org.sang.feign.hystrix;

import org.sang.commons.entity.Book;
import org.sang.service.HelloService;

/** 
* @author  作者:柯麒鹰  
* 			 E-mail:wangkeqing@e-eduwisdom.com
* @date 创建时间：2017年12月26日 上午10:20:07  
* 
* 
* Feign中如何配置Hystrix的服务降级呢？很简单，新建一个类，实现HelloService接口
* 这里方法实现的逻辑都是相应的服务降级逻辑。然后在@FeignClient注解中指定服务降级处理类即可
* @FeignClient(value = "hello-service",fallback = HelloServiceFallback.class)
*/
public class HelloServiceFallback implements HelloService {

	@Override
	public String hello(String name) {
		return "error " + name;
	}

	@Override
	public Book hello(String name, String author, Integer price) throws Exception {
		Book book = new Book();
		book.setName("error");
		return book;
	}

	@Override
	public String hello(Book book) {
		return "error book";
	}

}
