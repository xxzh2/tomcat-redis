package io.apache.redis.session.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import redis.clients.util.Pool;

public class ConnectionPoolTest {

	protected Pool<Jedis> connectionPool;
	protected JedisPoolConfig connectionPoolConfig = new JedisPoolConfig();

	public String getHost() {
		return "192.168.88.134";
	}

	public int getPort() {
		return 6379;
	}

	public int getTimeout() {
		return Protocol.DEFAULT_TIMEOUT;
	}

	public String getPassword() {
		return null;
	}

	@Test
	public void init() {
		connectionPoolConfig.setMinIdle(10);
		connectionPoolConfig.setMaxWaitMillis(10 * 1000);
		connectionPoolConfig.setMaxIdle(20);
		connectionPoolConfig.setMaxTotal(50);
		connectionPoolConfig.setTestOnBorrow(true);
		connectionPool = new JedisPool(this.connectionPoolConfig, getHost(), getPort());
		Jedis redis = connectionPool.getResource();
		
		connectionPool.returnResource(redis);
	}
}
