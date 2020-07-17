package com.study.anyang.service;

import java.util.List;

import com.study.anyang.repository.CodeGroupRepository;
import com.study.anyang.domain.CodeGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CodeGroupService {

	@Autowired
	private CodeGroupRepository repository;

	public void register(CodeGroup codeGroup) throws Exception {
		repository.save(codeGroup);
	}

	public CodeGroup read(String groupCode) throws Exception {
		return repository.getOne(groupCode);
	}

	public void modify(CodeGroup codeGroup) throws Exception {
		CodeGroup codeGroupEntity = repository.getOne(codeGroup.getGroupCode());

		codeGroupEntity.setGroupName(codeGroup.getGroupName());
		
		repository.save(codeGroupEntity);
	}

	public void remove(String groupCode) throws Exception {
		repository.deleteById(groupCode);
	}

	public List<CodeGroup> list() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "groupCode"));
	}
	
}
