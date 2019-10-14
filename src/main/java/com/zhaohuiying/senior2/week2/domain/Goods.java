package com.zhaohuiying.senior2.week2.domain;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * @ClassName: Goods 
 * @Description: TODO
 * @author:赵绘樱
 * @date: 2019年10月14日 上午10:36:49
 */
public class Goods implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private BigDecimal price;
	private String baifen;
	public Goods(int id, String name, BigDecimal price, String baifen) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.baifen = baifen;
	}
	public Goods() {
		super();
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", baifen=" + baifen + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getBaifen() {
		return baifen;
	}
	public void setBaifen(String baifen) {
		this.baifen = baifen;
	}
	
	
}
