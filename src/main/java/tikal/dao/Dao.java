package tikal.dao;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import tikal.model.Checkin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

@Repository
public class Dao {

	private final Jedis jedis = new Jedis("localhost");
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private static final String LAST_KEYS = "lastkeys";
	
	private static final long MAX_KEYS_LENGTH = 1000000L;
	
	public Dao() {
		
	}
	
	public void insertChecking(Checkin checkin) throws Exception {
		
		String json = mapper.writeValueAsString(checkin);
		
		String key = checkin.getTimestamp() + "." + checkin.getUserId();
		
		jedis.set(key, json);
		
		jedis.lpush(LAST_KEYS, key);
		
		if(jedis.llen(LAST_KEYS) > MAX_KEYS_LENGTH) {
			jedis.lpop(LAST_KEYS);
		}
		
	}
}
