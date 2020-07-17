package com.study.anyang.web;

import com.study.anyang.domain.CodeGroup;
import com.study.anyang.service.CodeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/codegroup")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CodeGroupController {

	@Autowired
	private CodeGroupService service;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerForm(Model model) throws Exception {
		CodeGroup codeGroup = new CodeGroup();

		model.addAttribute(codeGroup);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception {
		service.register(codeGroup);

		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codegroup/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		model.addAttribute("list", service.list());
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(String groupCode, Model model) throws Exception {
		model.addAttribute(service.read(groupCode));
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(String groupCode, RedirectAttributes rttr) throws Exception {

		service.remove(groupCode);

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/codegroup/list";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyForm(String groupCode, Model model) throws Exception {
		model.addAttribute(service.read(groupCode));
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception {
		service.modify(codeGroup);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/codegroup/list";
	}

}
