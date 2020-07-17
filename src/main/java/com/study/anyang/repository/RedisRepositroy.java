package com.study.anyang.repository;

import com.study.anyang.domain.RedisEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RedisRepositroy extends CrudRepository<RedisEntity, UUID> {
}
