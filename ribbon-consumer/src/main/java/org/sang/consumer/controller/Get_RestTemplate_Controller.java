package org.sang.consumer.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.sang.commons.entity.Book;
import org.sang.consumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/** 
* @author  作者:柯麒鹰  
* 			 E-mail:wangkeqing@e-eduwisdom.com
* @date 创建时间：2017年12月11日 下午2:57:40  
*/
@RestController
public class Get_RestTemplate_Controller {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HelloService service;
	
	@GetMapping("/ribbon-consumer")
	public String helloController() {
		//return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
		return service.hello(); 
	}
	
	@RequestMapping("/getHello")
	public String getHello() {
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class);
		String body = responseEntity.getBody();
		HttpHeaders headers = responseEntity.getHeaders();
		HttpStatus statusCode = responseEntity.getStatusCode();
		int statusCodeValue = responseEntity.getStatusCodeValue();
		StringBuffer result = new StringBuffer();
		result.append("responseEntity.getBody():").append(body).append("<hr>")
				.append("responseEntiry.getHeaders():").append(headers).append("<hr>")
				.append("responseEntiry.getStatusCode():").append(statusCode).append("<hr>")
				.append("responseEntity.getStatusCodeValue():").append(statusCodeValue).append("<hr>");
		return result.toString();
	}
	
	@RequestMapping("/sayhello")
	public String sayhello() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/sayhello?name={1}", String.class,"张三");
		String body = responseEntity.getBody();
		return body;
	}
	
	@RequestMapping("sayhello2")
	public String sayhello2() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "李四");
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/sayhello?name={name}", String.class,map);
		return responseEntity.getBody();
	}
	
	@RequestMapping("sayhello3")
	public String sayhello3() {
		UriComponents uriComponents = UriComponentsBuilder
						.fromUriString("http://HELLO-SERVICE/sayhello?name={name}")
						.build()
						.expand("王五")
						.encode();
		URI uri = uriComponents.toUri();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		return responseEntity.getBody();
	}
	
	@RequestMapping("book")
	@ResponseBody
	public Book book() {
		ResponseEntity<Book> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/book", Book.class);
		return responseEntity.getBody();
	}
	
	@RequestMapping("book2")
	@ResponseBody
	public Book book2() {
		Book book = restTemplate.getForObject("http://HELLO-SERVICE/book", Book.class);
		return book;
	}
	
	
}
