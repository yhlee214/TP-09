package lx.edu.gonggu.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.to.AttendanceTO;

@Repository
@RequiredArgsConstructor
public class AttendanceDAO {

	private final SqlSession session;
	
	public int createAtt(AttendanceTO attto) {
		return session.insert("mapper-att.createAtt", attto);
	}
	
	public List<AttendanceTO> getAttByPartyNo(int partyNo) {
		return session.selectList("mapper-att.getAttByPartyNo", partyNo);
	}
	
	public List<AttendanceTO> getAttListByUserNo(int userNo) {
		return session.selectList("mapper-att.getAttListByUserNo", userNo);
	}
	
	public int deleteAtt(int atNo) {
		return session.delete("mapper-att.deleteAtt", atNo);
	}
	
	public AttendanceTO getAttByAttNo(int attNo) {
		return session.selectOne("mapper-att.getAttByAttNo", attNo);
	}
	
	public List<AttendanceTO> getAttListByPartyOwner(int userNo) {
	    return session.selectList("mapper-att.getAttListByPartyOwner", userNo);
	}

}
