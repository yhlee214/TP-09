package lx.edu.gonggu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 로그인에 대한 인증 작업으로 .. 
public class LoginCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception{
		
		HttpSession session = req.getSession(false);
		
		if (session == null || session.getAttribute("loginUser") == null) {
			res.sendRedirect("/login");
			return false;
		}

		return true;
	}


}
