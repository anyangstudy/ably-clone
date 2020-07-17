package com.study.anyang.repository;

import java.util.List;

import com.study.anyang.domain.PdsFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdsFileRepository extends JpaRepository<PdsFile, Long> {
	
	public List<PdsFile> findByFullName(String fullName);
	
}
