package lx.edu.gonggu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.service.UserService;
import lx.edu.gonggu.to.UsersTO;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService service;

	// Get 요청을 통해서 해당하는 회원가입 폼으로 이동하는 페이지를 받습니다.
	@GetMapping("/signupform")
	public String signupForm() {
		return "/signupform";
	}

	// 중복 아이디 로그인 체크로 해당하는 요청에 데이터를 처리한 후 받은 결과를 상태코드로 보여줍니다. 200 , 404 500..
	@PostMapping("/dupCheck")
	public ResponseEntity<Boolean> checkUserIdDuplicate(@RequestParam("id") String userId) {

		boolean isDuplicate = service.isUserIdDuplicated(userId);

		return ResponseEntity.ok(isDuplicate);
	}
	// ajax통신을 했을 떄 중복인지 아닌지 true false 알려줘야 한다.

	// 회원가입을 통해 받은 requestBody객체 UserTO라는 그릇에 담는다. html name명과 일치해야 한다. userId 이런식으로
	@PostMapping("/signup")
	public String signup(UsersTO requestData) {

		service.createUser(requestData);

		return "redirect:/loginform";
	}

	// 로그인 페이지로 이동한다.
	@GetMapping({"/login", "/loginform"}) // 고친 이후 지금 회원가입 이후랑 로그아웃이 둘다 리턴 로그인 폼으로 보내고 있어서 수정
	public String loginForm() {
		return "/loginform";
	}

	// 로그인 버튼을 누를시에 요청
	@PostMapping("/login")
	public String login(UsersTO userTO, Model model, HttpServletRequest req) {

		UsersTO login = service.loginCheck(userTO.getUserId(), userTO.getUserPw());

		// 해당하는 로그인 정보가 없을 시에.. view화면에 보여줄 내용..
		if (login == null) {
			model.addAttribute("loginError", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "/loginform";
		}

		// 세션생성후 세션에 로그인 정보를 저장합니다. 그리고 메인뷰.. 화면으로 이동을 합니다.
		HttpSession session = req.getSession();
		session.setAttribute("loginUser", login);

		return "redirect:/list";

	}

	// 로그아웃시에 해당하는 세션에 정보를 invalidate() 함수를 통해 지우고 로그인 화면으로 이동합니다
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {

		HttpSession session = req.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		return "redirect:/loginform";

	}

}
