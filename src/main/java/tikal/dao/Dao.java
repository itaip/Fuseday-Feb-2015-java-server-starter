package tikal.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import tikal.model.Checkin;

import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class Dao {

	private final JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private static final String LAST_KEYS = "lastkeys";
	
	private static final long MAX_KEYS_LENGTH = 10L;
	
	public void insertChecking(Checkin checkin) throws Exception {

		Jedis jedis = pool.getResource();
		
		String json = mapper.writeValueAsString(checkin);
		
		String key = checkin.getTimestamp() + "." + checkin.getUserId();
		
		jedis.set(key, json);
		
		jedis.lpush(LAST_KEYS, key);
		
		if(jedis.llen(LAST_KEYS) > MAX_KEYS_LENGTH) {
			jedis.lpop(LAST_KEYS);
		}
		
		pool.returnResource(jedis);
		
	}
	
	public List<Checkin> getLastAttacks() throws Exception {
		
		Jedis jedis = pool.getResource();
		
		List<String> keys = jedis.lrange(LAST_KEYS, 0, -1);
		
		List<Checkin> result = new ArrayList<>();

		if(!keys.isEmpty()) {
			List<String> jsonList = jedis.mget(keys.toArray(new String[]{}));
			for(String json : jsonList) {
				result.add(mapper.readValue(json, Checkin.class));
			}
		}
		pool.returnResource(jedis);
		return result;
	}
}
