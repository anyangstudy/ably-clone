package com.study.anyang.common.service;

import java.util.List;

import com.study.anyang.common.domain.LoginLog;
import com.study.anyang.common.repository.LoginLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class LoginLogService {

    @Autowired
    private LoginLogRepository repository;

    public void register(LoginLog loginLog) throws Exception {
        repository.save(loginLog);
    }

    public List<LoginLog> list() throws Exception {
        return repository.findAll(Sort.by(Direction.DESC, "logNo"));
    }

}
