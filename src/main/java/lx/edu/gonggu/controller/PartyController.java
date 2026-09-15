package lx.edu.gonggu.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.service.AttendanceService;
import lx.edu.gonggu.service.PartyService;
import lx.edu.gonggu.to.AttendanceTO;
import lx.edu.gonggu.to.PartyTO;
import lx.edu.gonggu.to.UsersTO;

@Controller
@RequiredArgsConstructor
public class PartyController {
	
	private final PartyService ptService;
	private final AttendanceService attService;

	// 목록을 조회
	@GetMapping("/list")
	public String getMain(Model model) {		
		List<PartyTO> list = ptService.getPartyList();
		model.addAttribute("list", list);
		return "/list";
	}
	// 공구글 작성 폼으로 이동
	@GetMapping("/postform")
	public String getPostForm() {
		
		return "/postform";
	}
	
	// 공구글 작성 
	@PostMapping("/postform")
	public String writeForm(PartyTO partyTO, HttpSession session) {
		UsersTO loginUser = (UsersTO) session.getAttribute("loginUser");
		partyTO.setUserNo(loginUser.getUserNo());
		partyTO.setPartyDateTime(LocalDateTime.now());
		partyTO.setRecentUpdate(LocalDateTime.now());
		ptService.createParty(partyTO);
		
		return "redirect:/list";
	}
	
	@GetMapping("/post/{no}")
	public String detail(Model model, @PathVariable("no") int no, HttpSession session) {
		
		UsersTO loginUser = (UsersTO) session.getAttribute("loginUser");
		
		PartyTO party = ptService.getPartyDetail(no);
		List<AttendanceTO> attList = attService.getAttList(no);
		//ApplyTO applyList 
		
		boolean isUser = loginUser != null && loginUser.getUserNo() ==party.getUserNo();
		
		model.addAttribute("party", party);
		model.addAttribute("userName", party.getUserName());
		model.addAttribute("attList", attList);
		// 추후 댓글도 포함.
		model.addAttribute("isUser", isUser);
		
		return "/postdetail";
	}

	@PostMapping("/postdelete/{no}")
	public String delete(@PathVariable("no") int no, HttpSession session) {
		
		UsersTO loginUser = (UsersTO) session.getAttribute("loginUser");
		
		ptService.deleteParty(no, loginUser.getUserNo());
		
		return "redirect:/list";
	}
	
	
}
