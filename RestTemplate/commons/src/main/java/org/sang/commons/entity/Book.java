package org.sang.commons.entity;

import java.io.Serializable;

/** 
* @author  作者:柯麒鹰  
* 			 E-mail:wangkeqing@e-eduwisdom.com
* @date 创建时间：2017年12月13日 上午10:14:43  
*/
/**
 * @author 柯麒鹰
 *
 */
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private Integer price;
	private String Author;
	private String Press;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String name, Integer price, String author, String press) {
		super();
		this.name = name;
		this.price = price;
		Author = author;
		Press = press;
	}

	public String getName() {
		return name;
	}

	public Book setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getPrice() {
		return price;
	}

	public Book setPrice(Integer price) {
		this.price = price;
		return this;
	}

	public String getAuthor() {
		return Author;
	}

	public Book setAuthor(String author) {
		Author = author;
		return this;
	}

	public String getPress() {
		return Press;
	}

	public Book setPress(String press) {
		Press = press;
		return this;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", price=" + price + ", Author=" + Author + ", Press=" + Press + "]";
	}

}
