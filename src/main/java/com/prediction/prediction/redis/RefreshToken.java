//package com.prediction.prediction.redis;
//
//import org.springframework.data.redis.core.RedisHash;
//import org.springframework.data.redis.core.TimeToLive;
//import org.springframework.data.redis.core.index.Indexed;
//
//import jakarta.persistence.Id;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@Builder
//@RedisHash(value = "refresh_token")
//public class RefreshToken {
//
//    @Id
//    private String authId;
//
//    @Indexed
//    private String token;
//
//    private String role;
//
//    @TimeToLive
//    private long ttl;
//
//    public RefreshToken update(String token, long ttl) {
//        this.token = token;
//        this.ttl = ttl;
//        return this;
//    }
//}
