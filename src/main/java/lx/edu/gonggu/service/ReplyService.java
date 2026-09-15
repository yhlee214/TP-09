package lx.edu.gonggu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.dao.ReplyDAO;
import lx.edu.gonggu.to.ReplyTO;

@Service
@RequiredArgsConstructor
public class ReplyService {

	private final ReplyDAO dao;
	
	public void addReply(ReplyTO reply) {
		int result = dao.createReply(reply);
		
		if(result > 0) {
			System.out.println("댓글 추가");
		} else {
			System.out.println("댓글 추가 실패");
		}
		
	}
	
	public List<ReplyTO> showReply(int partyNo) {
		List<ReplyTO> result = dao.getReplyListByPartyNo(partyNo);
		
		if(result != null) {
			System.out.println("댓글 보이는 중");
			return result;
		} else {
			System.out.println("댓글 없음");
			return null;
		}
		
	}
		
	public void editReply(ReplyTO reply) {
		int result = dao.updateReply(reply);
		
		if(result > 0) {
			System.out.println("댓글 추가");
		} else {
			System.out.println("댓글 추가 실패");
		}
		
	}
	
	public void deleteReply(int replyNo) {
		int result = dao.deleteReply(replyNo);
		
		if(result > 0) {
			System.out.println("댓글 삭제");
		} else {
			System.out.println("댓글 삭제 실패");
		}
		
	}
	
}
