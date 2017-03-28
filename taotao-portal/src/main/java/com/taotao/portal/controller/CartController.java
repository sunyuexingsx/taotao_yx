package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

/**
 * 购物车商品
 * <p>
 * 永远相信自己一定会成为想象中的人
 * <p>
 * 
 * @author 刘强
 * @date 2017年1月5日 上午10:08:27
 * @file_name CartController.java
 * @project_name taotao-portal
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable long itemId,
			@RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		TaotaoResult taotaoResult = cartService.addCartItem(itemId, num, request, response);
		return "redirect:/cart/success.html";
	}
	
	@RequestMapping("/success")
	public String showSuccess(){
		return "cartSuccess";
	}
	
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request,HttpServletResponse response,Model model){
		List<CartItem> itemList = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", itemList);
		return "cart";
	}
	
	@RequestMapping("/delete/${cart.id}")
	public String deleteCartItem(@PathVariable long itemId,HttpServletResponse response,HttpServletRequest request){
		TaotaoResult result = cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}
}
