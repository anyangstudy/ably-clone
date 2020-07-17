package com.study.anyang.web;

import com.study.anyang.domain.Notice;
import com.study.anyang.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService service;

	@GetMapping(value = "/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void registerForm(Model model) throws Exception {
		Notice notice = new Notice();

		model.addAttribute(notice);
	}

	@PostMapping(value = "/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String register(Notice notice, RedirectAttributes rttr) throws Exception {
		service.register(notice);

		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/notice/list";
	}

	@GetMapping(value = "/list")
	public void list(Model model) throws Exception {
		model.addAttribute("list", service.list());
	}

	@GetMapping(value = "/read")
	public void read(Long noticeNo, Model model) throws Exception {
		model.addAttribute(service.read(noticeNo));
	}

	@PostMapping(value = "/remove")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String remove(Long noticeNo, RedirectAttributes rttr) throws Exception {
		service.remove(noticeNo);

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/notice/list";
	}

	@GetMapping(value = "/modify")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void modifyForm(Long noticeNo, Model model) throws Exception {
		model.addAttribute(service.read(noticeNo));
	}

	@PostMapping(value = "/modify")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String modify(Notice notice, RedirectAttributes rttr) throws Exception {
		service.modify(notice);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/notice/list";
	}

}
