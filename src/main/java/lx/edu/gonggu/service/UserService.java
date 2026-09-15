package lx.edu.gonggu.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.dao.UsersDAO;
import lx.edu.gonggu.to.UsersTO;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UsersDAO dao;

	public UsersTO createUser(UsersTO usersTO) {
	
		UsersTO user = new UsersTO();
		user.setUserId(usersTO.getUserId());
		user.setUserPw(usersTO.getUserPw());
		user.setUserName(usersTO.getUserName());
		user.setUserPhone(usersTO.getUserPhone()); 
		user.setParcelAddr(usersTO.getParcelAddr());
		user.setRoadAddr(usersTO.getRoadAddr());
		user.setRegionNo(usersTO.getRegionNo());
		
		dao.createUser(user);
		
		return user;	
	}
	
	public boolean isUserIdDuplicated(String userId) {
		
		String checkId = dao.findByUserId(userId);
		
		return checkId != null;
			
	}
	
	public UsersTO loginCheck(String userId, String userPwd) {
			
		UsersTO user = dao.findUserForLogin(userId);
	
		if(user == null) {
			return null;
		}
		
		if(!user.getUserPw().equals(userPwd)) {
			return null;
		}
		
		return user;
		
		
	}
	
}
