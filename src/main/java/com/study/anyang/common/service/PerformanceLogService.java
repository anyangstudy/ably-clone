package com.study.anyang.common.service;

import com.study.anyang.common.domain.PerformanceLog;
import com.study.anyang.common.repository.PerformanceLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceLogService {
    @Autowired
    private PerformanceLogRepository repository;

    public void register(PerformanceLog performanceLog) throws Exception {
        repository.save(performanceLog);
    }

    public List<PerformanceLog> list() throws Exception {
        return repository.findAll(Sort.by(Direction.DESC, "logNo"));
    }

}
