package org.sang.service;

import org.sang.commons.entity.Book;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** 
* @author  作者:柯麒鹰  
* 			 E-mail:wangkeqing@e-eduwisdom.com
* @date 创建时间：2017年12月25日 下午3:16:06  
*/
@RequestMapping("/hs2")
public interface HelloService {

	@RequestMapping(value="/hello",method = RequestMethod.GET)
	String hello(@RequestParam("name") String name);
	
	@RequestMapping(value="/hello2",method = RequestMethod.GET)
	Book hello(@RequestHeader("name") String name,@RequestHeader("author") String author,@RequestHeader("price") Integer price) throws Exception;
	
	@RequestMapping(value="/hello3",method = RequestMethod.POST)
	String hello(@RequestBody Book book);
}
