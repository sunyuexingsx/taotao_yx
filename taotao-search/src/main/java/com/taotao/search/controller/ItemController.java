package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




import com.taotao.common.utils.TaotaoResult;
import com.taotao.search.service.ItemService;

/**
 * 
 * <p>永远相信自己一定会成为想象中的人<p>
 * @author 刘强
 * @date   2017年1月4日  上午11:02:37
 * @file_name   ItemController.java
 * @project_name   taotao-search
 */
@Controller
@RequestMapping("/manager")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	/**
	 * 导入商品数据到索引库
	 */
	@RequestMapping("/importall")
	@ResponseBody
	public TaotaoResult importAllItems() {
		TaotaoResult result = itemService.importAllItems();
		return result;
	}
}
