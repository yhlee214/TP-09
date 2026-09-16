package lx.edu.gonggu.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.to.UsersTO;

@Repository
@RequiredArgsConstructor
public class UsersDAO {

	private final SqlSession session;
	
	// 회원가입용
	public int createUser(UsersTO user) {
		return session.insert("mapper-users.createUser", user);
	}
		
	public String findByUserId(String userId) {
		return session.selectOne("mapper-users.findByUserId", userId);
	}
	
	public UsersTO findUserForLogin(String userId) {
		return session.selectOne("mapper-users.findUserForLogin", userId);
	}
	
	public UsersTO getUserInfoForMypage(String userNo) {
		return session.selectOne("mapper-users.getUserInfoForMyPage", userNo);
	}
}
