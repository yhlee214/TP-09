package lx.edu.gonggu.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.to.UserTO;

@Repository
@RequiredArgsConstructor
public class UserDAO {
	
	private final SqlSession session;
	
	public int createUser(UserTO user) {
		return session.insert("mapper-user.createUser", user);
	}

	public String findByUserId(String userId) {
		return session.selectOne("mapper-user.findByUserId", userId);
	}
	
	public UserTO findUserForLogin(String userId) {
		return session.selectOne("mapper-user.findUserForLogin", userId);
	}
}
