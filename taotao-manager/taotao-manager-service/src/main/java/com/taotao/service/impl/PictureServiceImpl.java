package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

/*
 * 图片上传服务
 */
@Service
public class PictureServiceImpl implements PictureService {
	
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	@Override
	public Map uploadPicure(MultipartFile uploadFile) {
		Map<Object, Object> resultMap = new HashMap<>();
		try {
		//生成新的文件名
		//取原始文件名
		String filename = uploadFile.getOriginalFilename();
		//生成新的文件名
//		UUID uuid = UUID.randomUUID();
		String name = IDUtils.genImageName();
		name = name + filename.substring(filename.lastIndexOf("."));
		String imagePathString = new DateTime().toString("yyyy/MM/dd");
		//图片上传
		boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, 
				imagePathString, filename, uploadFile.getInputStream());
		//返回结果
		if(!result){
			resultMap.put("error", 1);
			resultMap.put("message", "上传失败");
			return resultMap;
		}
		resultMap.put("error", 0);
		resultMap.put("url", IMAGE_BASE_URL+imagePathString+"/"+filename);
		return resultMap;
		} catch (IOException e) {
			resultMap.put("error", 1);
			resultMap.put("message", "上传失败");
			return resultMap;
		}
	}

}
