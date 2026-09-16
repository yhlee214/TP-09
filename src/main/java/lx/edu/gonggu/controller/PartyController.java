package lx.edu.gonggu.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.service.AttendanceService;
import lx.edu.gonggu.service.PartyService;
import lx.edu.gonggu.service.ReplyService;
import lx.edu.gonggu.to.AttendanceTO;
import lx.edu.gonggu.to.PartyTO;
import lx.edu.gonggu.to.ReplyTO;
import lx.edu.gonggu.to.UsersTO;

@Controller
@RequiredArgsConstructor
public class PartyController {
	
	private final PartyService ptService;
	private final AttendanceService attService;
	private final ReplyService replyService;

	// 목록을 조회
	@GetMapping("/list")
	public String getMain(Model model) {		
		List<PartyTO> list = ptService.getPartyList();
		model.addAttribute("partyList", list);
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
		partyTO.setRegionNo(loginUser.getRegionNo());	// 글 지역 = 작성자 동네 (폼의 동 선택 제거)
		partyTO.setPartyDateTime(LocalDateTime.now());
		partyTO.setRecentUpdate(LocalDateTime.now());
		partyTO.setUserName(loginUser.getUserName());
		ptService.createParty(partyTO);
		
		return "redirect:/list";
	}
	
	@GetMapping("/post/detail")
	public String detail(Model model, @RequestParam("partyNo") int no, HttpSession session) {
		
		UsersTO loginUser = (UsersTO) session.getAttribute("loginUser");
		
		PartyTO party = ptService.getPartyDetail(no);
		List<AttendanceTO> attList = attService.getAttList(no);
		List<ReplyTO> replyList = replyService.showReply(no); 
		//ApplyTO applyList 
		
		boolean isUser = loginUser != null && loginUser.getUserNo() ==party.getUserNo();
		
		model.addAttribute("party", party);
		model.addAttribute("userName", party.getUserName());
		model.addAttribute("attList", attList);
		model.addAttribute("replyList", replyList);
		model.addAttribute("isUser", isUser);
		
		return "/postdetail";
	}

	@PostMapping("/post/delete")
	public String delete(@RequestParam("partyNo") int no, HttpSession session) {
		
		UsersTO loginUser = (UsersTO) session.getAttribute("loginUser");
		
		ptService.deleteParty(no, loginUser.getUserNo());
		
		return "redirect:/list";
	}
	
	@GetMapping("/main")
	public String main() {
	    return "redirect:/list";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.setDisallowedFields("productImg");   // 파일은 커맨드 객체에 바인딩하지 않음
	}
}
