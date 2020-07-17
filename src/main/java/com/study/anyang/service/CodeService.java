package com.study.anyang.service;

import java.util.ArrayList;
import java.util.List;

import com.study.anyang.dto.CodeLabelValue;
import com.study.anyang.repository.CodeDetailRepository;
import com.study.anyang.repository.CodeGroupRepository;
import com.study.anyang.domain.CodeDetail;
import com.study.anyang.domain.CodeGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

	@Autowired
	private CodeGroupRepository repository;
	
	@Autowired
	private CodeDetailRepository codeDetailRepository;
	
	public List<CodeLabelValue> getCodeGroupList() throws Exception {
		List<CodeGroup> codeGroups = repository.findAll(Sort.by(Direction.ASC, "groupCode"));
		
		List<CodeLabelValue> codeGroupList = new ArrayList<CodeLabelValue>();
		
		for(CodeGroup codeGroup : codeGroups) {
			codeGroupList.add(new CodeLabelValue(codeGroup.getGroupCode(), codeGroup.getGroupName()));
		}
		
		return codeGroupList;
	}
	
	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception {
		List<CodeDetail> codeDetails = codeDetailRepository.getCodeList(groupCode);
		
		List<CodeLabelValue> codeList = new ArrayList<CodeLabelValue>();
		
		for(CodeDetail codeDetail : codeDetails) {
			codeList.add(new CodeLabelValue(codeDetail.getCodeValue(), codeDetail.getCodeName()));
		}
		
		return codeList;
	}

}
