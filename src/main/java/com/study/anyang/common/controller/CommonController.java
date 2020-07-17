package com.study.anyang.common.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.java.Log;

@Log
@Controller
public class CommonController {

    @RequestMapping(value = "/error/errorCommon", method = RequestMethod.GET)
    public void handleCommonError() {
        log.info("errorCommon");
    }

    @RequestMapping(value = "/error/accessError", method = RequestMethod.GET)
    public void accessDenied(Authentication auth, Model model) {
        log.info("access Denied : " + auth);

        model.addAttribute("msg", "Access Denied");
    }

}
