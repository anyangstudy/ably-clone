package com.study.anyang.repository;

import com.study.anyang.domain.ChargeCoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeCoinRepository extends JpaRepository<ChargeCoin, Long> {
	
}
