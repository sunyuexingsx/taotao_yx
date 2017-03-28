package com.taiotao.rest.solrj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {
	
	@Test
	public void addDocument() throws Exception{
		//创建solr连接
		SolrServer server = new HttpSolrServer("http://101.200.237.120:8080/solr");
		//创建文档对象
		SolrInputDocument sDocument = new SolrInputDocument();
		sDocument.addField("id", "test001");
		sDocument.addField("item_title", "测试商品2");
		sDocument.addField("item_price", 54321);
		//把文档对象写入索引库
		server.add(sDocument);
		//关闭连接
		server.commit();
	}
}
