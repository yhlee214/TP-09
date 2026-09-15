package lx.edu.gonggu.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.to.ReplyTO;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {
	
	private final SqlSession session;
	
	public int createReply(ReplyTO repto) {
		return session.insert("mapper-reply.createReply", repto);
	}
	
	public int update(ReplyTO repto) {
		return session.update("mapper-reply.updateReply", repto);
	}
	
	public int deleteReply(ReplyTO repto) {
		return session.delete("mapper-reply.deleteReply", repto);
	}

}
