package org.sang.provider.controller;

import org.sang.commons.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
* @author  作者:柯麒鹰  
* 			 E-mail:wangkeqing@e-eduwisdom.com
* @date 创建时间：2017年12月15日 下午1:18:15  
*/

@Controller
public class BookController {

	@GetMapping("/getBook5/{id}")
	@ResponseBody
	public Book book5(@PathVariable Integer id) {
		System.err.println(">>>>>>/getBook5/{id}");
		if(id == 1) {
			return new Book("《李自成》",55,"姚雪垠","人民文学出版社~~~");
		}else if(id == 2) {
			return new Book("中国文学简史",55,"林庚","清华大学出版社");
		}
		return new Book("文学改良刍议",99,"胡适","无");
	}
}
