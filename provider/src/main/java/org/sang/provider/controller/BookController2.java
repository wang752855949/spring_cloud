package org.sang.provider.controller;

import java.net.URLDecoder;

import org.sang.commons.entity.Book;
import org.sang.service.HelloService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
* @author  作者:柯麒鹰  
* 			 E-mail:wangkeqing@e-eduwisdom.com
* @date 创建时间：2017年12月25日 下午3:33:36  
*/
@RestController
public class BookController2 implements HelloService{

	@Override
	public String hello(@RequestParam("name")String name) {
		return "hello"+name;
	}

	@Override
	public Book hello(@RequestHeader("name") String name, @RequestHeader("author") String author, @RequestHeader("price") Integer price) throws Exception {
		Book book = new Book();
		book.setName(URLDecoder.decode(name,"UTF-8"))
			.setAuthor(URLDecoder.decode(author,"UTF-8"))
			.setPrice(price);
		System.err.println(book);
		return book;
	}

	@Override
	public String hello(@RequestBody Book book) {
		return "書名為:"+book.getName() + ";作者為:"+ book.getAuthor();
	}

}
