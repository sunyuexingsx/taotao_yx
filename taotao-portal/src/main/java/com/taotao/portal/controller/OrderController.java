package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;

/**
 * 
 * <p>永远相信自己一定会成为想象中的人<p>
 * @author 刘强
 * @date   2017年1月6日  下午8:58:10
 * @file_name   OrderController.java
 * @project_name   taotao-portal
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request,HttpServletResponse response,Model model){
		//取购物车商品列表
		List<CartItem> itemList = cartService.getCartItemList(request, response);
		//传递给页面
		model.addAttribute("cartList", itemList);
		return "order-cart";
	}
	
	@RequestMapping("/create")
	public String createOrder(Order order,Model model){
		try {
			String orderId = orderService.createOrder(order);
			model.addAttribute("orderId", orderId);
			model.addAttribute("payment", order.getPayment());
			model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", ExceptionUtil.getStackTrace(e));
			return "error/exception";
		}
	}
}
