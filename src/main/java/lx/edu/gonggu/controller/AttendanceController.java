package lx.edu.gonggu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.service.AttendanceService;
import lx.edu.gonggu.to.AttendanceTO;
import lx.edu.gonggu.to.UsersTO;

@Controller
@RequiredArgsConstructor
public class AttendanceController {

	private final AttendanceService service;
	
	@PostMapping("/apply")
	public String addAttendance(AttendanceTO attTo, HttpSession session) {
		UsersTO loginUser = (UsersTO) session.getAttribute("loginUser");
		attTo.setUserNo(loginUser.getUserNo());	
		service.createAtt(attTo);
		
		return "redirect:/post/"+attTo.getPartyNo();
	}
	
	@PostMapping("/apply/{no}")
	public String deleteAttendance(@PathVariable("no") int attNo, HttpSession session) {
		UsersTO loginUser = (UsersTO) session.getAttribute("loginUser");
		
		int partyNo = service.deleteAtt(attNo, loginUser.getUserNo());
		
		return "redirect:/post/" +partyNo;
	}
	
	@PostMapping("/apply/approve")
	public String approve(@PathVariable("no") int attNo, HttpSession session) {
		UsersTO loginUser = (UsersTO) session.getAttribute("loginUser");
		service.approveAtt(attNo, loginUser.getUserNo());
		
		return "redirect:/mypage";
	}
	
	@PostMapping("/apply/reject")
	public String reject(@PathVariable("no") int attNo, HttpSession session) {
		UsersTO loginUser = (UsersTO) session.getAttribute("loginUser");
		service.rejectAtt(attNo, loginUser.getUserNo());
		
		return "redirect:/mypage";
	}
	
	
}
