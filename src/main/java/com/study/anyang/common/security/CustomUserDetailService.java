package com.study.anyang.common.security;

import com.study.anyang.common.security.domain.CustomUser;
import com.study.anyang.domain.Member;
import com.study.anyang.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.extern.java.Log;

@Log
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("userName : " + userName);

        Member member = repository.findByUserId(userName).get(0);

        log.info("member : " + member);

        return member == null ? null : new CustomUser(member);
    }

}
