package com.study.anyang.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@RedisHash("user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RedisEntity {
    @Id
    private UUID id;

    private String name;
}
