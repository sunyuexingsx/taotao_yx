package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

/**
 * 
 * <p>永远相信自己一定会成为想象中的人<p>
 * @author 刘强
 * @date   2016年12月31日  下午12:40:50
 * @file_name   UserService.java
 * @project_name   taotao-portal
 */
public interface UserService {
	
	TbUser getUserByToken(String token);
}
