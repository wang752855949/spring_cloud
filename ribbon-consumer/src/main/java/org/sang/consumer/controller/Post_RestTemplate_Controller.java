package org.sang.consumer.controller;
/** 
* @author  作者:柯麒鹰  
* 			 E-mail:wangkeqing@e-eduwisdom.com
* @date 创建时间：2017年12月13日 上午11:48:29  
*/

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.sang.commons.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Post_RestTemplate_Controller {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String url = "http://HELLO-SERVICE";

	@RequestMapping("book3")
	public Book book3() {
		Book book = new Book();
		book.setName("红楼梦");
		ResponseEntity<Book> postForEntity = restTemplate.postForEntity(url+"/book3"	, book, Book.class);
		return postForEntity.getBody();
	}

	@RequestMapping("book4")
	public Book book4() {
		Book book = new Book();
		book.setName("西游记");
		book = restTemplate.postForObject(url+"/book4", book, Book.class);
		return book;
	}
	
	
	/*@RequestMapping("book5")
	public Map<String,Object> book5() throws Exception {
		Book book = new Book();
		book.setName("水浒传");
		URI uri = restTemplate.postForLocation(url+"/book5", book);
		System.err.println(uri.toString());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uri", uri);
		return map;
	}*/
}
