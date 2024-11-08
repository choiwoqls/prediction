package com.prediction.prediction.util;

import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.NotFoundException;
import com.prediction.prediction.exception.ResourceNotFoundException;
import com.prediction.prediction.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveToken(String key, String token, long expirationTimeInMinutes) {
        try {
            redisTemplate.opsForValue().set(key, token, Duration.ofMinutes(expirationTimeInMinutes));
        } catch (RedisConnectionFailureException e) {
            System.err.println("Redis 서버에 연결할 수 없습니다: " + e.getMessage());
            // 로깅 및 알림 추가 가능
            throw new NotFoundException(e.getMessage());
        } catch (DataAccessException e) {
            System.err.println("Redis 데이터 액세스 예외 발생: " + e.getMessage());
            // 예외 전파 또는 기본 처리
            throw new NotFoundException(e.getMessage());
        }
    }

    public String getToken(String key) {
        try {
            String token = (String) redisTemplate.opsForValue().get(key);
            if (token == null) {
                throw new UnauthorizedException("Unauthorized Key");
            }
            return token;
        }catch (RedisConnectionFailureException e) {
            System.err.println("Redis 서버에 연결할 수 없습니다: " + e.getMessage());
            throw new NotFoundException(e.getMessage());
        }catch (DataAccessException e) {
            System.err.println("Redis 데이터 액세스 예외 발생: " + e.getMessage());
            throw new NotFoundException(e.getMessage());
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    public void deleteToken(String key) {
        try {
            redisTemplate.delete(key);
        } catch (RedisConnectionFailureException e) {
            System.err.println("Redis 서버에 연결할 수 없습니다: " + e.getMessage());
            throw new NotFoundException(e.getMessage());
        } catch (DataAccessException e) {
            System.err.println("Redis 데이터 액세스 예외 발생: " + e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
    }

    public void matchedToken(String key, String token) throws ResourceNotFoundException {
        if(!this.getToken(key).equals(token)){
            throw new UnauthorizedException("Redis Not Matched");
        }
    }
}
