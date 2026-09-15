package lx.edu.gonggu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.dao.AttendanceDAO;
import lx.edu.gonggu.to.AttendanceTO;

@Service
@RequiredArgsConstructor
public class AttendanceService {
	
	private final AttendanceDAO dao;
	
	public List<AttendanceTO> getAttList(int partyNo) {
		return dao.getAttByPartyNo(partyNo);
	}
	
	public int createAtt(AttendanceTO attTo) {
		return dao.createAtt(attTo);
	}
	
	public int deleteAtt(int attNo, int userNo) {
		AttendanceTO att = dao.getAttByAttNo(attNo);
		
		if(att == null) {
			throw new IllegalArgumentException("존재하지 않는 신청입니다.");
		}
		
		if(att.getUserNo() != userNo) {
			throw new SecurityException("본인이 신청한 내역만 삭제 가능합니다.");
		}
		dao.deleteAtt(attNo);
		return att.getPartyNo(); 
		
	}
	
	
}
