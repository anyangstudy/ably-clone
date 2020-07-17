package com.study.anyang.common.service;

import java.util.List;

import com.study.anyang.common.domain.AccessLog;
import com.study.anyang.common.repository.AccessLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class AccessLogService{

    @Autowired
    private AccessLogRepository repository;

    public void register(AccessLog accessLog) throws Exception {
        repository.save(accessLog);
    }

    public List<AccessLog> list() throws Exception {
        return repository.findAll(Sort.by(Direction.DESC, "logNo"));
    }

}
