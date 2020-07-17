package com.study.anyang.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.study.anyang.domain.MemberAuth;
import com.study.anyang.repository.MemberRepository;
import com.study.anyang.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberRepository repository;
	
	public long countAll() throws Exception {
		return repository.count();
	}
	
	public void setupAdmin(Member member) throws Exception {
		Member memberEntity = new Member();
		memberEntity.setUserId(member.getUserId());
		memberEntity.setUserPw(member.getUserPw());
		memberEntity.setUserName(member.getUserName());

		MemberAuth memberAuth = new MemberAuth();
		memberAuth.setAuth("ROLE_ADMIN");
		
		memberEntity.addAuth(memberAuth);
		
		repository.save(memberEntity);
	}

	public void register(Member member) throws Exception {
		Member memberEntity = new Member();
		memberEntity.setUserId(member.getUserId());
		memberEntity.setUserPw(member.getUserPw());
		memberEntity.setUserName(member.getUserName());

		MemberAuth memberAuth = new MemberAuth();
		memberAuth.setAuth("ROLE_MEMBER");
		
		memberEntity.addAuth(memberAuth);
		
		repository.save(memberEntity);
	}
	
	public Member read(Long userNo) throws Exception {
		return repository.getOne(userNo);
	}

	public void modify(Member member) throws Exception {
		Member memberEntity = repository.getOne(member.getUserNo());
		memberEntity.setUserName(member.getUserName());

		List<MemberAuth> memberAuthList = memberEntity.getAuthList();
		List<MemberAuth> authList = member.getAuthList();
		for(int i = 0; i < authList.size(); i++) {
			MemberAuth auth = authList.get(i);
			
			if(i < memberAuthList.size()) {
				MemberAuth memberAuth = memberAuthList.get(i);
				memberAuth.setAuth(auth.getAuth());
			}
		}
		
		repository.save(memberEntity);
	}

	public void remove(Long userNo) throws Exception {
		repository.deleteById(userNo);
	}

	public List<Member> list() throws Exception {
		List<Object[]> valueArrays = repository.listAllMember();
		
		List<Member> memberList = new ArrayList<Member>();
		for(Object[] valueArray : valueArrays) {
			Member member = new Member();
			
			member.setUserNo((Long)valueArray[0]);
			member.setUserId((String)valueArray[1]);
			member.setUserPw((String)valueArray[2]);
			member.setUserName((String)valueArray[3]);
			member.setCoin((int)valueArray[4]);
			member.setRegDate((Date)valueArray[5]);
			
			memberList.add(member);
		}
		
		return memberList;
	}
	
	public int getCoin(Long userNo) throws Exception {
		Member member = repository.getOne(userNo);
		
		return member.getCoin();
	}
	
}
