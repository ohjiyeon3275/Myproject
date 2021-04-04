package kr.co.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.service.MemberService;
import kr.co.vo.MemberVO;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	public MemberService service;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String showJoin() throws Exception{
		
		return "common/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception{
		logger.info("post register");
		
		service.register(vo);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLogin() throws Exception{
		
		return "common/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		logger.info("post login");
		
		HttpSession session = req.getSession();
		MemberVO login = service.login(vo);
		
		if(login == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		}else {
			session.setAttribute("member", login);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		
		return "redirect:/";
	}
	
	
}
