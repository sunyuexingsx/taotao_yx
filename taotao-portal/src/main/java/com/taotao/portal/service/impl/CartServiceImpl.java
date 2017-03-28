package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Value("${ITME_INFO_URL}")
	private String ITME_INFO_URL;

	/**
	 * 
	  * <p>Title: addCartItem</p>
	  * <p>Description: </p>
	  * @param itemId
	  * @param num
	  * @param request
	  * @param response
	  * @return
	  * @see com.taotao.sso.service.UserService#userLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public TaotaoResult addCartItem(long itemId, int num,
			HttpServletRequest request, HttpServletResponse response) {
		// 取商品信息
		CartItem cartItem = null;
		// 先取购物车商品列表
		List<CartItem> itemlist = getCartItemList(request);
		// 判断购物车商品列表中是否存在商品
		for (CartItem cItem : itemlist) {
			// 如果存在商品
			if (cItem.getId() == itemId) {
				// 增加数量
				cItem.setNum(cItem.getNum() + num);
				cartItem = cItem;
				break;
			}
		}
		if (cartItem == null) {
			cartItem = new CartItem();
			// 根据商品id查询商品基本信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITME_INFO_URL
					+ itemId);
			// 把json转换城java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json,
					TbItem.class);
			if (taotaoResult.getStatus() == 200) {
				TbItem item = (TbItem) taotaoResult.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setPrice(item.getPrice());
				cartItem.setNum(num);
				cartItem.setImage(item.getImage() == null ? "" : item
						.getImage().split(",")[0]);
			}
			// 添加到购物车列表
			itemlist.add(cartItem);
		}
		// 把购物车列表写入cookie
		CookieUtils.setCookie(request, response, "TT_CART",
				JsonUtils.objectToJson(itemlist), true);
		return TaotaoResult.ok();
	}

	/**
	 * 封装一个取购物车商品列表的方法(即：从cookie中取商品列表)
	 * @param request
	 * @return
	 */
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		// 从cookie中取商品列表
		String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
		if (cartJson == null) {
			return new ArrayList<>();
		}
		// 把json转换成商品列表
		try {
			List<CartItem> list = JsonUtils
					.jsonToList(cartJson, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	
	/**
	 * 
	  * <p>Title: getCartItemList</p>
	  * <p>Description: </p>
	  * @param request
	  * @param response
	  * @return
	  * @see com.taotao.sso.service.UserService#userLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request,
			HttpServletResponse response) {
		List<CartItem> itemList = getCartItemList(request);
		return itemList;
	}
	
	
	/**
	 * 删除购物车商品
	  * <p>Title: deleteCartItem</p>
	  * <p>Description: </p>
	  * @param itemId
	  * @return
	  * @see com.taotao.sso.service.UserService#userLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public TaotaoResult deleteCartItem(long itemId,HttpServletRequest request,HttpServletResponse response) {
		// 从cookie中取出购物车商品
		List<CartItem> itemList = getCartItemList(request);
		//从列表中找到商品
		for (CartItem cartItem : itemList) {
			if(cartItem.getId() == itemId){
				itemList.remove(cartItem);
				break;
			}
		}
		//把购物车列表重新写入cookie中
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		return TaotaoResult.ok();
	}

}
