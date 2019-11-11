package com.lw.common.redis;



import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lw.common.utils.JudgeUtils;
import com.lw.common.utils.StringUtils;

@Service(value = "redisService")
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void setValue(String key, Object value) {
        String jsonValue = JSON.toJSONString(value, SerializerFeature.WriteClassName, SerializerFeature.WriteNullListAsEmpty);
        stringRedisTemplate.opsForValue().set(key, jsonValue);
    }

    public void setValue(String key, Object value, final long timeout, final TimeUnit unit) {
        String jsonValue = JSON.toJSONString(value, SerializerFeature.WriteClassName, SerializerFeature.WriteNullListAsEmpty);
        stringRedisTemplate.opsForValue().set(key, jsonValue, timeout, unit);
    }

    public void delete(String key) {
        if (StringUtils.isNotEmpty(key)) {
            stringRedisTemplate.delete(key);
        }

    }

    public void deleteByKeys(Set<String> keys) {
        if (JudgeUtils.isNotEmpty(keys)) {
            stringRedisTemplate.delete(keys);
        }
    }

    public Set keys(String pattern) {
        return stringRedisTemplate.keys("*" + pattern + "*");
    }

    public Set leftKeys(String pattern) {
        return stringRedisTemplate.keys("*" + pattern);
    }

    public Set rightKeys(String pattern) {
        return stringRedisTemplate.keys(pattern + "*");
    }

    public long getExpire(String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public String getValue(String key) {
        if (StringUtils.isNotEmpty(key)) {
            String s = stringRedisTemplate.opsForValue().get(key);
            return s;
        }
        return null;
    }

    public Object getValue(String key, Class<?> cls) {
        if (StringUtils.isNotEmpty(key)) {
            String value = stringRedisTemplate.opsForValue().get(key);
            if (value != null) {
                return JSON.parseObject(value, cls);
            }
        }
        return null;
    }

    public <T> T getValueByType(String key, Class<T> cls) {
        if (StringUtils.isNotEmpty(key)) {
            String value = stringRedisTemplate.opsForValue().get(key);
            if (value != null) {
                return JSON.parseObject(value, cls);
            }
        }
        return null;
    }


    public Object getValueOfArray(String key, Class<?> cls) {
        if (StringUtils.isNotEmpty(key)) {
            String value = stringRedisTemplate.opsForValue().get(key);
            if (value != null) {
                return JSON.parseArray(value, cls);
            }
        }
        return null;
    }

    public <T> List<T> getValueOfArrayByType(String key, Class<T> cls) {
        if (StringUtils.isNotEmpty(key)) {
            String value = stringRedisTemplate.opsForValue().get(key);
            if (value != null) {
                return JSON.parseArray(value, cls);
            }
        }
        return null;
    }


    public boolean existKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public int incrAndGet(String key, long timeout, TimeUnit timeUnit) {
        RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
//        int incr = redisAtomicInteger.getAndIncrement();
        int incr = redisAtomicInteger.incrementAndGet();
        //可设置过期时间
        if (incr > 0 && timeout > 0 && timeUnit != null) {
            redisAtomicInteger.expire(timeout, timeUnit);
        }
        return incr;

    }


    public void expire(String key, long seconds, TimeUnit unit) {
        redisTemplate.expire(key, seconds, unit);
    }

    public void rpush(String key, Object value) {
        if (StringUtils.isNotEmpty(key)) {
            ListOperations<String, Object> list = redisTemplate.opsForList();
            list.rightPush(key, value);
        }
    }

    public Object lpop(String key) {
        if (StringUtils.isNotEmpty(key)) {
            return redisTemplate.opsForList().leftPop(key);
        }
        return null;
    }

    public <T> T lpop(String key, Class<T> cls) {
        if (StringUtils.isNotEmpty(key)) {
            return (T) redisTemplate.opsForList().leftPop(key);
        }
        return null;
    }

    public List<Object> lrange(String key, long start, long end) {
        if (StringUtils.isNotEmpty(key)) {
            ListOperations<String, Object> list = redisTemplate.opsForList();
            return list.range(key, start, end);
        }
        return null;
    }

    public <T>List<T> leftRange(String key, long start, long end) {
        if (StringUtils.isNotEmpty(key)) {
            ListOperations<String, Object> list = redisTemplate.opsForList();
            return (List<T>)list.range(key, start, end);
        }
        return null;
    }

    public void ltrim(String key, long start, long end) {
        if (StringUtils.isNotEmpty(key)) {
            ListOperations<String, Object> list = redisTemplate.opsForList();
            list.trim(key, start, end);
        }
    }

    public long llen(String key) {
        long res = 0l;
        if (StringUtils.isNotEmpty(key)) {
            ListOperations<String, Object> list = redisTemplate.opsForList();
            res = list.size(key);
        }
        return res;
    }

    /**
     * 冒号分隔，模糊匹配
     * @param pattern
     * @return
     */
    public Set<String> getKeysWithColon(String ...pattern) {
        StringBuilder sb = new StringBuilder();
        for(String each : pattern) {
            sb.append(each);
            sb.append("*");
        }
        return redisTemplate.keys(sb.toString());
    }
    
    /**
     * 只是获取字符串，不转json
     * @param key
     * @return
     */
    public String getStringValue(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return value;
    }
}
