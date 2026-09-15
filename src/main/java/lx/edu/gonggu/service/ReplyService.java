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
		if (result <= 0) {
			throw new IllegalStateException("댓글 등록에 실패했습니다.");
		}

	}

	public List<ReplyTO> showReply(int partyNo) {
		List<ReplyTO> result = dao.getReplyListByPartyNo(partyNo);

		if (result != null) {
			System.out.println("댓글 보이는 중");
			return result;
		} else {
			System.out.println("댓글 없음");
			return null;
		}

	}

	public void editReply(ReplyTO reply, int userNo) {
		ReplyTO to = dao.getReplyByReplyNo(reply.getReplyNo());
		if (to == null) {
			throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
		}
		if (to.getUserNo() != userNo) {
			throw new SecurityException("본인이 작성한 댓글만 수정할 수 있습니다.");
		}
		int result = dao.updateReply(reply);
		if (result <= 0) {
			throw new IllegalStateException("댓글 수정에 실패했습니다.");
		}
	}

	public void deleteReply(int replyNo, int userNo) {
		  ReplyTO reply = dao.getReplyByReplyNo(replyNo);
	        if (reply == null) {
	            throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
	        }
	        if (reply.getUserNo() != userNo) {
	            throw new SecurityException("본인이 작성한 댓글만 삭제할 수 있습니다.");
	        }
	        int result = dao.deleteReply(replyNo);
	        if (result <= 0) {
	            throw new IllegalStateException("댓글 삭제에 실패했습니다.");
	        }
	}

}
