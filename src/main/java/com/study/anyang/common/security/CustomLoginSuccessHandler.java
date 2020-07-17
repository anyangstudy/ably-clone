package com.study.anyang.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.anyang.domain.Member;
import com.study.anyang.common.domain.LoginLog;
import com.study.anyang.common.security.domain.CustomUser;
import com.study.anyang.common.service.LoginLogService;
import com.study.anyang.common.util.NetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.java.Log;

@Log
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private LoginLogService service;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUser customUser = (CustomUser)authentication.getPrincipal();
        Member member = customUser.getMember();

        log.info("Userid = " + member.getUserId());

        String remoteAddr = NetUtils.getIp(request);

        log.info("remoteAddr = " + remoteAddr);

        LoginLog loginLog = new LoginLog();

        loginLog.setUserNo(member.getUserNo());
        loginLog.setUserId(member.getUserId());
        loginLog.setRemoteAddr(remoteAddr);

        try {
            service.register(loginLog);
        } catch (Exception e) {

        }

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
