package tikal.dao;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import tikal.model.Checkin;

import com.google.common.collect.ImmutableMap;

@Repository
public class Dao {

	private final Jedis jedis = new Jedis("localhost");
	
	public Dao() {
		
	}
	
	public void insertChecking(Checkin checkin) {
		
		Map<String, String> hash = ImmutableMap.of(
				"latitude", String.valueOf(checkin.getLatitude()),
				"longitude", String.valueOf(checkin.getLongitude()),
				"userId", checkin.getUserId(),
				"timestamp", String.valueOf(checkin.getTimestamp()));
		jedis.hmset(UUID.randomUUID().toString(), hash);
	}
}
