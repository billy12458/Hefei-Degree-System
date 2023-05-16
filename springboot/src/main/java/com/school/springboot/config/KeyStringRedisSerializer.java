package com.school.springboot.config;

import java.io.UnsupportedEncodingException;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class KeyStringRedisSerializer implements RedisSerializer<String> {

    private final String CACHE_PREFIX = "shiro:";

    private final String SHIRO_LOGIN = "shiro:loginUsers:";

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.data.redis.serializer.RedisSerializer#deserialize(byte[])
     */
    @Override
    public String deserialize(@Nullable byte[] bytes) {
        if (new String(bytes).contains(SHIRO_LOGIN)) {
            return new String(bytes).replaceFirst(SHIRO_LOGIN, "");
        }
        if (bytes == null) {
            return null;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.data.redis.serializer.RedisSerializer#serialize(java.lang
     * .Object)
     */
    @Override
    public byte[] serialize(@Nullable String string) {
        try {
            return (string == null ? null : (CACHE_PREFIX + string).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
