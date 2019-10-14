package com.zhaohuiying.senior2.week2;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhaohuiying.common.StreamUtils;
import com.zhaohuiying.common.StringUtil;
import com.zhaohuiying.senior2.week2.domain.Goods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ReadGoods {
	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;
	
	/**
	 * 
	 * @Title: testread 
	 * @Description: 读取数据存进redis
	 * @throws Exception
	 * @return: void
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testread() throws Exception {
		List<String> str = StreamUtils.readTextForLine(new FileInputStream("F:\\workspace\\1075F_CMS\\zhaohuiying-senior2-week2\\src\\test\\resources\\shuju.txt"));
		Goods goods = new Goods();
		
		for (String line : str) {
			String[] split = line.split("==");
			for (String shuju : split) {
				System.out.println(shuju);
				for (int i = 0; i < split.length; i++) {
					

//					
//					a)	ID值要使用isNumber()工具方法判断是不是数字
					boolean testid = StringUtil.isNumber(split[0]);
					System.out.println(testid);
//					b)	商品名称要使用hasText()方法判断有没有值
					boolean testname = StringUtil.hasText(split[1]);
					System.out.println(testname);
//					c)	价格要使用hasText()方法判断有没有值，并使用isNumber()判断是不是数字（不是数字的可以手工处理好再解析）。然后去掉“¥”符号，再转成数字。
					boolean testprice = StringUtil.hasText(split[2]);
					System.out.println(split[2]);
					String str1 = split[2].replace("¥", "");
					boolean testprice2 = StringUtil.isNumber(str1);
					System.out.println(testprice2);
					System.out.println(testprice);
//					d)	百分比使用hasText()方法判断有没有值，如果没值则默认为0，并使用isNumber()判断是不是数字。然后去掉“%”符号，再转成数字。
					boolean testbaifen = StringUtil.hasText(split[3]);
					System.out.println(split[2]);
					String str2 = split[2].replace("%", "");
					boolean testbaifen2 = StringUtil.isNumber(str2);
					System.out.println(testbaifen);
					System.out.println(testbaifen2);
//					e)	将每行数据解析到Goods类中，总共106条数据，少1行扣2分，最多扣20分。
					goods.setId(Integer.parseInt(split[0].trim()));
					goods.setName(split[1]);
					goods.setPrice(BigDecimal.valueOf(Double.parseDouble(str1.trim())));
					goods.setBaifen(split[3]);
				}
				
				
			}
			
			redisTemplate.opsForList().leftPush("goods_list", goods);
		}
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testzset() throws Exception {
		List<String> str = StreamUtils.readTextForLine(new FileInputStream("F:\\workspace\\1075F_CMS\\zhaohuiying-senior2-week2\\src\\test\\resources\\shuju.txt"));
		String str1 ="";
		Goods goods = new Goods();
		for (String line : str) {
			String[] split = line.split("==");
			for (String shuju : split) {
				System.out.println(shuju);
				for (int i = 0; i < split.length; i++) {
				
					
//					
//					a)	ID值要使用isNumber()工具方法判断是不是数字
					boolean testid = StringUtil.isNumber(split[0]);
					System.out.println(testid);
//					b)	商品名称要使用hasText()方法判断有没有值
					boolean testname = StringUtil.hasText(split[1]);
					System.out.println(testname);
//					c)	价格要使用hasText()方法判断有没有值，并使用isNumber()判断是不是数字（不是数字的可以手工处理好再解析）。然后去掉“¥”符号，再转成数字。
					boolean testprice = StringUtil.hasText(split[2]);
					System.out.println(split[2]);
					str1 = split[2].replace("¥", "");
					boolean testprice2 = StringUtil.isNumber(str1);
					System.out.println(testprice2);
					System.out.println(testprice);
//					d)	百分比使用hasText()方法判断有没有值，如果没值则默认为0，并使用isNumber()判断是不是数字。然后去掉“%”符号，再转成数字。
					boolean testbaifen = StringUtil.hasText(split[3]);
					System.out.println(split[2]);
					String str2 = split[2].replace("%", "");
					boolean testbaifen2 = StringUtil.isNumber(str2);
					System.out.println(testbaifen);
					System.out.println(testbaifen2);
//					e)	将每行数据解析到Goods类中，总共106条数据，少1行扣2分，最多扣20分。
					goods.setId(Integer.parseInt(split[0].trim()));
					goods.setName(split[1]);
					goods.setPrice(BigDecimal.valueOf(Double.parseDouble(str1.trim())));
					goods.setBaifen(split[3]);
				}

			}			
			redisTemplate.opsForZSet().add("goods_zset", goods,Double.parseDouble(str1.trim()));
			
		}
	}
}
