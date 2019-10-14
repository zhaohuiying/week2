package com.zhaohuiying.senior2.week2.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhaohuiying.senior2.week2.domain.Goods;
/**
 * 
 * @ClassName: GoodsController 
 * @Description: TODO
 * @author:赵绘樱
 * @date: 2019年10月14日 上午10:36:35
 */
@Controller
public class GoodsController {
	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;
	
	/***
	 * 
	 * @Title: finlistall 
	 * @Description: 查询list
	 * @param m
	 * @param page
	 * @param pagesize
	 * @return
	 * @return: String
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("finlistall")
	public String finlistall(Model m,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pagesize) {
		//获取方法查询 分页查询
		List<Goods> list = redisTemplate.opsForList().range("goods_list", (page-1)*pagesize,(page-1)*pagesize+pagesize-1);
//		System.out.println(list);
		m.addAttribute("list", list);
		//封装上一页下一页
		if(pagesize!=1) {
			m.addAttribute("prepage", page-1);
		}else {
			m.addAttribute("prepage", 1);
		}
		//进入list页面
		m.addAttribute("nextpage", page+1);
		return "redislist";
	}
	/**
	 * 
	 * @Title: finzetall 
	 * @Description: 查询zset
	 * @param m
	 * @param page
	 * @param pagesize
	 * @return
	 * @return: String
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("finzetall")
	public String finzetall(Model m,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pagesize) {
		
		
//		List<Goods> list = redisTemplate.opsForList().range("goods_list", (page-1)*pagesize,(page-1)*pagesize+pagesize-1);
		//获取方法查询
		Set<Goods> list = redisTemplate.opsForZSet().reverseRange("goods_zset", (page-1)*pagesize, (page-1)*pagesize+pagesize-1);
		
//		System.out.println(list);
		m.addAttribute("list", list);
		//封装上一页下一页 分页查询
		if(pagesize!=1) {
			m.addAttribute("prepage", page-1);
		}else {
			m.addAttribute("prepage", 1);
		}
		m.addAttribute("nextpage", page+1);
		//进入list页面
		return "rediszset";
	}
}
