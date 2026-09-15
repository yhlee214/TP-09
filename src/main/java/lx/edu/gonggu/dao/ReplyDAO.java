package lx.edu.gonggu.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.to.ReplyTO;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {
	
	private final SqlSession session;
	
	public int createReply(ReplyTO reply) {
		return session.insert("mapper-reply.createReply", reply);
	}
	
	public List<ReplyTO> getReplyListByPartyNo(int partyNo) {
		return session.selectList("mapper-reply.getReplyListByPartyNo", partyNo);
	}
	
	public int updateReply(ReplyTO reply) {
		return session.update("mapper-reply.updateReply", reply);
	}
	
	public int deleteReply(int replyNo) {
		return session.delete("mapper-reply.deleteReply", replyNo);
	}
	
	public ReplyTO getReplyByReplyNo(int replyNo) {
	    return session.selectOne("mapper-reply.getReplyByReplyNo", replyNo);
	}

}
