package shop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * TODO 调用redis相应的方法没有抛出异常，就说明操作一定成功了吗？？
 */
@Component
public class RedisUtil {
	@Autowired
	private RedisTemplate<String,Object> redis;
	
	public RedisUtil() {};
	
	public boolean set(String key,Object value) {
		try{
			redis.opsForValue().set(key, value);
			return true;
		}catch(Exception ignored){
			return false;
		}
	}
	
	public Object get(String key) {
		return redis.opsForValue().get(key);
	}
	
	public boolean listRightPush(String key,Object value) {
		try{
			redis.opsForList().rightPush(key, value);
			return true;
		}catch(Exception ignored) {
			return false;
		}
	}	
	
	public boolean listRightPop(String key) {
		try{
			redis.opsForList().rightPop(key);
			return true;
		}catch(Exception ignored) {
			return false;
		}
	}
	

}
