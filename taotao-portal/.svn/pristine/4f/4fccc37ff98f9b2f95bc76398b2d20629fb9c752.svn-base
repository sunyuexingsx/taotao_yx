package com.taotao.httpclient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

	@Test
	public void doGet() throws Exception {
		// 创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个get对象
		HttpGet get = new HttpGet("http://www.sogou.com");
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		// 取响应的结果
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		// 关闭httpclient
		response.close();
		httpClient.close();
	}

	@Test
	public void doGetWithParam() throws Exception {
		// 创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		URIBuilder uriBuilder = new URIBuilder("http://www.sogou.com/web");
		uriBuilder.addParameter("query", "花千骨");
		HttpGet get = new HttpGet(uriBuilder.build());
		CloseableHttpResponse response = httpClient.execute(get);
		// 取响应的结果
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		// 关闭httpclient
		response.close();
		httpClient.close();
	}

	@Test
	public void doPost() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpPost httpPost = new HttpPost(
				"http://localhost:8082//httpclient/post.action");
		// 执行post请求
		CloseableHttpResponse response = client.execute(httpPost);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		client.close();
	}

	@Test
	public void doPostWithParam() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpPost httpPost = new HttpPost(
				"http://localhost:8082//httpclient/post.html");
		//创建一个Entity,模拟一个表单
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("username", "liuqiang"));
		kvList.add(new BasicNameValuePair("password", "liuqiang57"));
		//包装成一个Entity对象
		StringEntity entity = new UrlEncodedFormEntity(kvList,"utf-8");
		//设置请求的内容
		httpPost.setEntity(entity);
		// 执行post请求
		CloseableHttpResponse response = client.execute(httpPost);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		client.close();
	}
}
