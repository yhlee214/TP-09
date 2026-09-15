package lx.edu.gonggu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.service.ReplyService;
import lx.edu.gonggu.to.ReplyTO;
import lx.edu.gonggu.to.UsersTO;

@Controller
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService service;

	// 댓글 추가
	@PostMapping("/comment/write")
	public String addReply(HttpSession session, ReplyTO reply) {
		UsersTO loginUser = (UsersTO) session.getAttribute("loginUser");
		reply.setUserNo(loginUser.getUserNo());
		service.addReply(reply);
		return "redirect:/post/detail?partyNo=" + reply.getPartyNo();
	}

	// 댓글 수정
	@PostMapping("/comment/edit")
	public String editReply(HttpSession session, ReplyTO reply) {
		UsersTO loginUser = (UsersTO) session.getAttribute("loginUser");
		service.editReply(reply, loginUser.getUserNo());
		return "redirect:/post/detail?partyNo=" + reply.getPartyNo();
	}

	// 댓글 삭제
	@PostMapping("/comment/delete")
	public String deleteReply(HttpSession session, @RequestParam("replyNo") int replyNo,
            @RequestParam("partyNo") int partyNo) {
		UsersTO loginUser = (UsersTO) session.getAttribute("loginUser");
		service.deleteReply(replyNo, loginUser.getUserNo());
		return "redirect:/post/detail?partyNo=" + partyNo;
	}

}
