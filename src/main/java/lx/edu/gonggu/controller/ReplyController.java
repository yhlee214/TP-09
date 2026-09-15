package lx.edu.gonggu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.service.ReplyService;
import lx.edu.gonggu.to.ReplyTO;

@Controller
@RequiredArgsConstructor
public class ReplyController {
	
	private final ReplyService service;
	
	// 댓글 추가
	@PostMapping("/reply")
	public String addReply(HttpSession session, ReplyTO reply) {		
		service.addReply(reply);	
		return "redirect:/list";	
	}
	
	// 댓글 조회
//	@PostMapping("/showreply")
//	public String showReply(HttpSession session, int partyNo) {
//		List<ReplyTO> replyList = service.showReply(partyNo);
//		model.addAttribute("replyList", replyList);
//		return "";
//	}
	
	// 댓글 수정
	@PostMapping("/editreply")
	public String editReply(HttpSession session, ReplyTO reply) {		
		service.editReply(reply);
		return "redirect:/list";
	}
	
	// 댓글 삭제
	@PostMapping("/deletereply")
	public String deleteReply(HttpSession session, int replyNo) {	
		service.deleteReply(replyNo);
		return "redirect:/list";
	}
	
}
