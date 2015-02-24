package tikal.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import tikal.model.Checkin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class Dao {

	private final Jedis jedis = new Jedis("localhost");
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private static final String LAST_KEYS = "lastkeys";
	
	private static final long MAX_KEYS_LENGTH = 10L;
	
	public void insertChecking(Checkin checkin) throws Exception {
		
		String json = mapper.writeValueAsString(checkin);
		
		String key = checkin.getTimestamp() + "." + checkin.getUserId();
		
		jedis.set(key, json);
		
		jedis.lpush(LAST_KEYS, key);
		
		if(jedis.llen(LAST_KEYS) > MAX_KEYS_LENGTH) {
			jedis.lpop(LAST_KEYS);
		}
		
	}
	
	public List<Checkin> getLastAttacks() throws Exception {
		
		List<String> keys = jedis.lrange(LAST_KEYS, 0, -1);
		List<String> jsonList = jedis.mget(keys.toArray(new String[]{}));
		List<Checkin> result = new ArrayList<>();
		for(String json : jsonList) {
			result.add(mapper.readValue(json, Checkin.class));
		}
		return result;
	}
}
