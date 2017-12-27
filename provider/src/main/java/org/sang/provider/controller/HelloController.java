package org.sang.provider.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.sang.commons.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String index() {
		List<ServiceInstance> instances = client.getInstances("hello_service");
		for(int i = 0 ;i <instances.size(); i++) {
			logger.info("/************************,host:"+instances.get(i).getHost()+",service_id:"+instances.get(i).getServiceId());
		}
		return "hello world~~~~";
	}
	
	@RequestMapping("/sayhello")
	@ResponseBody
	public String sayHello(String name) {
		return name;
	}
	
	@RequestMapping("/book")
	@ResponseBody
	public Book getBook() {
		return new Book("三国演义", 90, "罗贯中", "花城出版社");
	}
	
	@PostMapping("book3")
	@ResponseBody
	public Book getBook2(@RequestBody Book book) {
		book.setPrice(30);
		book.setAuthor("曹雪芹");
		book.setPress("人民文学出版社");
		return book;
	}
	
	@PostMapping("book4")
	@ResponseBody
	public Book getBook4(@RequestBody Book book) {
		book.setPrice(90)
			.setPress("人教出版社")
			.setAuthor("罗贯中");
		return book;
	}
	
	/*@PostMapping("book5")
	public URI getBook5(@RequestBody Book book,HttpServletRequest req) throws Exception {
		book.setPrice(90)
			.setPress("人教出版社")
			.setAuthor("罗贯中");
		System.err.println(book.toString());
		System.err.println(req.getRequestURI());
		String uri = req.getRequestURI();
		return new URI(uri);
	}*/
}
