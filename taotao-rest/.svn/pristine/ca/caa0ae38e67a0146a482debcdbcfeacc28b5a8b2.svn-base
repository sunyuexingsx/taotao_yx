package com.taiotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	@Test
	public void testJedisSingle() {
		// 创建一个jedis对象
		Jedis jedis = new Jedis("101.200.237.120", 6379);
		// 调用jedis对象的方法，方法名和jedis的命令一致
		jedis.set("key1", "jedisTest");
		String string = jedis.get("key1");
		System.out.println(string);
		// 关闭jedis
		jedis.close();
	}

	/**
	 * 使用连接池
	 */
	@Test
	public void testJedisPool() {
		// 创建jedis连接池
		JedisPool jedisPool = new JedisPool("101.200.237.120", 6379);
		// 从连接池中获取jedis对象
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		jedisPool.close();
	}

	@Test
	public void testJedisCluster() {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("101.200.237.120", 7001));
		nodes.add(new HostAndPort("101.200.237.120", 7002));
		nodes.add(new HostAndPort("101.200.237.120", 7003));
		nodes.add(new HostAndPort("101.200.237.120", 7004));
		nodes.add(new HostAndPort("101.200.237.120", 7005));
		nodes.add(new HostAndPort("101.200.237.120", 7006));
		JedisCluster cluster = new JedisCluster(nodes);

		cluster.set("key1", "1000");
		String string = cluster.get("key1");
		System.out.println(string);

		cluster.close();
	}

	/**
	 * 单机版jedis容器测试
	 */
	@Test
	public void testSpringJedisSingle() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		pool.close();
		jedis.close();
	}

	/**
	 * 集群版jedis测试
	 */
	@Test
	public void testSpringJedisCluster() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("redisClient");
		String string = jedisCluster.get("key1");
		System.out.println(string);
		jedisCluster.close();
	}
}
