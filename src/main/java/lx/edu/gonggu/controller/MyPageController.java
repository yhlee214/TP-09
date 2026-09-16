package lx.edu.gonggu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.service.AttendanceService;
import lx.edu.gonggu.service.MyPageService;
import lx.edu.gonggu.service.PartyService;
import lx.edu.gonggu.service.ReplyService;
import lx.edu.gonggu.to.UsersTO;

@Controller
@RequiredArgsConstructor
public class MyPageController {
	
	private final PartyService ptService;
	private final AttendanceService attService;
	private final ReplyService replyService;
	
	@GetMapping("/mypage")
	public String getMyPage(Model model, HttpSession session) {
		UsersTO loginUser = (UsersTO) session.getAttribute("loginUser");
		
		int userNo = loginUser.getUserNo();
		model.addAttribute("myPartyList", ptService.getPartyListByUserNo(userNo));
		model.addAttribute("myAttList", attService.getAttListByUserNo(userNo));
		model.addAttribute("receivedList", attService.getAttListByPartyOwner(userNo));
		model.addAttribute("myReplyList", replyService.showReplyListByUserNo(userNo));
		
		return "/mypage";
	}
}
