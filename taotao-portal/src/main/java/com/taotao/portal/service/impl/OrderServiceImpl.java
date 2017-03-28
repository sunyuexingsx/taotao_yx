package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.OrderService;

/**
 * 
 * <p>永远相信自己一定会成为想象中的人<p>
 * @author 刘强
 * @date   2017年1月7日  上午11:39:57
 * @file_name   OrderServiceImpl.java
 * @project_name   taotao-portal
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	
	@Override
	public String createOrder(Order order) {
		//调用taotao-order服务提交订单
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		//将json转换城TaotaoResult
		TaotaoResult taotaoResult = TaotaoResult.format(json);
		if(taotaoResult.getStatus() == 200){
			Integer orderId = (Integer) taotaoResult.getData();
			return orderId.toString();
		}
		return "";
	}

}
