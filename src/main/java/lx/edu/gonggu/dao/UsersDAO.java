package lx.edu.gonggu.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.to.UserTO;

@Repository
@RequiredArgsConstructor
public class UsersDAO {

	private final SqlSession session;
	
	// 회원가입용
	public int createUser(UserTO user) {
		return session.insert("mapper-users.createUser", user);
	}
	
	
	public String findByUserId(String userId) {
		return session.selectOne("mapper-users.findByUserId", userId);
	}
	
	
	public UserTO findUserForLogin(String userId) {
		return session.selectOne("mapper-users.findUserForLogin", userId);
	}
	
	public UserTO getUserInfoForMypage(String userNo) {
		return session.selectOne("mapper-users.getUserInfoForMypage", userNo);
	}
}
