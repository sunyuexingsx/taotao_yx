package com.taotao.portal.service.impl;

import org.apache.taglibs.standard.extra.spath.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;

/**
 * 用户管理service
 * <p>
 * 永远相信自己一定会成为想象中的人
 * <p>
 * 
 * @author 刘强
 * @date 2016年12月31日 下午12:42:23
 * @file_name UserServiceImpl.java
 * @project_name taotao-portal
 */
@Service
public class UserServiceImpl implements UserService {

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;

	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	
	@Value("${SSO_PAGE_URL}")
	public String SSO_PAGE_URL;

	@Override
	public TbUser getUserByToken(String token) {
		try {
			// 调用sso系统的服务，根据token获取用户的信息
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN
					+ token);
			// 将json转换成Taotaoresul
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
			if (result.getStatus() == 200) {
				TbUser user = (TbUser) result.getData();
				return user;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
