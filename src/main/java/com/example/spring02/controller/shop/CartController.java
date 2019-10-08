package com.example.spring02.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;

@Controller
@RequestMapping("shop/cart/*")
public class CartController {

	@Autowired
	CartService cartService;

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session,ModelAndView mav) {
		Map<String,Object> map = new HashMap<>();
		String userid=(String)session.getAttribute("userid");
		System.out.println("세션 : " + userid);
		if(userid != null) {
			List<CartDTO> list = cartService.listCart(userid);
			System.out.println("list 에 머가 들었나 : " + list);
			map.put("list",list);
			map.put("count",list.size());
			mav.setViewName("shop/cart_list");
			mav.addObject("map", map);
		} else {
			return new ModelAndView("member/login","",null);
		}
		return mav;
	}
}
