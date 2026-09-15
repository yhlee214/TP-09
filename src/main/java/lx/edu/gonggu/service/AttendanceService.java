package lx.edu.gonggu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.dao.AttendanceDAO;
import lx.edu.gonggu.dao.PartyDAO;
import lx.edu.gonggu.to.AttendanceTO;
import lx.edu.gonggu.to.PartyTO;

@Service
@RequiredArgsConstructor
public class AttendanceService {
	
	private final AttendanceDAO dao;
	private final PartyDAO partyDao;
	
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
	
	public List<AttendanceTO> getAttListByPartyOwner(int userNo) {
	    return dao.getAttListByPartyOwner(userNo);
	}
	
	public List<AttendanceTO> getAttListByUserNo(int userNo) {
		return dao.getAttListByUserNo(userNo);
	}

	// 2개이상의 C U D 작업시
	@Transactional
	public void approveAtt(int attNo, int partyOwnUserNo) {
		AttendanceTO att = dao.getAttByAttNo(attNo);
		if(att == null) {
			throw new IllegalArgumentException("존재하지 않는 신청입니다.");
		}
		
		PartyTO party = partyDao.getPartyByPartyNo(att.getPartyNo());
		if(party.getUserNo() != partyOwnUserNo) {
			throw new SecurityException("본인 공구글의 신청만 승인할 수 있습니다.");
		}
		
		dao.updateAttStatus(attNo, "승인");
		partyDao.increaseAccumQty(att.getPartyNo(), att.getAttQty());
	}
	
	public void rejectAtt(int attNo, int partyOwnerUserNo) {
		AttendanceTO att = dao.getAttByAttNo(attNo);
		if(att == null) {
			throw new IllegalArgumentException("존재하지 않는 신청입니다.");
		}
		
		PartyTO party = partyDao.getPartyByPartyNo(att.getPartyNo());
		if(party.getUserNo() != partyOwnerUserNo) {
			throw new SecurityException("본인 공구글의 신청만 거절할 수 있습니다.");
		}
		
		dao.updateAttStatus(attNo, "거절");
	}

}
