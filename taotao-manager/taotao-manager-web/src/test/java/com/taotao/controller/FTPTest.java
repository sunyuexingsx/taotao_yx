package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPTest {

	@Test
	public void testFtpClient() throws Exception{
		//创建一个ftpClient对象
		FTPClient ftpClient = new FTPClient();
		//创建ftp连接
		ftpClient.connect("101.200.237.120", 21);
		//登录ftp服务器
		ftpClient.login("ftpuser", "ftpuser");
		//上传文件
		//读取本地文件
		FileInputStream iStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\1.jpg"));
		//设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/images");
		//修改文件上传的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//第一个参数：服务器文件名                 第二个参数：上传文件的InputStream
		ftpClient.storeFile("nihao.jpg", iStream);
		//关闭连接
		ftpClient.logout();
	}
}
