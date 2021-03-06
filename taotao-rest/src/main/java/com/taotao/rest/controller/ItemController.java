package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.rest.service.ItemService;

/**
 * 
 * <p>永远相信自己一定会成为想象中的人<p>
 * @author 刘强
 * @date   2017年1月3日  下午2:30:30
 * @file_name   ItemController.java
 * @project_name   taotao-rest
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public TaotaoResult getItemBaseInfo(@PathVariable long itemId){
		TaotaoResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	
	
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable long itemId){
		TaotaoResult result = itemService.getItemDesc(itemId);
		return result;
	}
	
	
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParam(@PathVariable long itemId){
		TaotaoResult result = itemService.getItemParam(itemId);
		return result;
	}
}
