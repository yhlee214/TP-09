package lx.edu.gonggu.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.dao.UserDAO;
import lx.edu.gonggu.to.UserTO;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserDAO dao;

	public UserTO createUser(UserTO userTO) {
	
		UserTO user = new UserTO();
		user.setUserId(userTO.getUserId());
		user.setUserPwd(userTO.getUserPwd());
		user.setUserName(userTO.getUserName());
		user.setUserPhone(userTO.getUserPhone());
		user.setUserNickname(userTO.getUserNickname());
		user.setUserAddr(userTO.getUserAddr());
		
		dao.createUser(user);
		
		return user;	
	}
	
	public boolean isUserIdDuplicated(String userId) {
		
		String checkId = dao.findByUserId(userId);
		
		return checkId != null;
			
	}
	
	public UserTO loginCheck(String userId, String userPwd) {
			
		UserTO user = dao.findUserForLogin(userId);
	
		if(user == null) {
			return null;
		}
		
		if(!user.getUserPwd().equals(userPwd)) {
			return null;
		}
		
		return user;
		
		
	}
	
}
