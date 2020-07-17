package com.study.anyang.service;

import java.util.List;

import com.study.anyang.repository.CodeDetailRepository;
import com.study.anyang.domain.CodeDetail;
import com.study.anyang.domain.CodeDetailId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CodeDetailService{

	@Autowired
	private CodeDetailRepository repository;

	public void register(CodeDetail codeDetail) throws Exception {
		String groupCode = codeDetail.getGroupCode();
		List<Object[]> rsList = repository.getMaxSortSeq(groupCode);
		
		Integer maxSortSeq = 0;
		if(rsList.size() > 0) {
			Object[] maxValues = rsList.get(0);
			System.out.println(maxValues);
			if(maxValues != null && maxValues.length > 0) {
				maxSortSeq = (Integer)maxValues[0];
			}
		}
	
		codeDetail.setSortSeq(maxSortSeq + 1);
		
		repository.save(codeDetail);
	}

	public CodeDetail read(CodeDetail codeDetail) throws Exception {
		CodeDetailId codeDetailId = new CodeDetailId();
		codeDetailId.setGroupCode(codeDetail.getGroupCode());
		codeDetailId.setCodeValue(codeDetail.getCodeValue());
		
		return repository.getOne(codeDetailId);
	}

	public void modify(CodeDetail codeDetail) throws Exception {
		CodeDetailId codeDetailId = new CodeDetailId();
		codeDetailId.setGroupCode(codeDetail.getGroupCode());
		codeDetailId.setCodeValue(codeDetail.getCodeValue());
		
		CodeDetail codeDetailEntity = repository.getOne(codeDetailId);

		codeDetailEntity.setCodeValue(codeDetail.getCodeValue());
		codeDetailEntity.setCodeName(codeDetail.getCodeName());
		
		repository.save(codeDetailEntity);
	}

	public void remove(CodeDetail codeDetail) throws Exception {
		CodeDetailId codeDetailId = new CodeDetailId();
		codeDetailId.setGroupCode(codeDetail.getGroupCode());
		codeDetailId.setCodeValue(codeDetail.getCodeValue());
		
		repository.deleteById(codeDetailId);
	}

	public List<CodeDetail> list() throws Exception {
		return repository.findAll(Sort.by(Direction.ASC, "groupCode", "codeValue"));
	}
	
}
