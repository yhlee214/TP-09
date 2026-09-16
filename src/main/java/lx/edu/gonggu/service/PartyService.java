package lx.edu.gonggu.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.dao.PartyDAO;
import lx.edu.gonggu.to.PartyTO;

@Service
@RequiredArgsConstructor
public class PartyService {

	private final PartyDAO dao;

	public List<PartyTO> getPartyList() {
		return dao.getPartyList();
	}

	public PartyTO getPartyDetail(int partyNo) {
		PartyTO party = dao.getPartyByPartyNo(partyNo);

		if (party == null) {
			throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
		}

		return party;
	}

	public void createParty(PartyTO partyTO) {
		
		if (partyTO.getTargetQty() <= 0) {
			throw new IllegalArgumentException("목표수량은 1개 이상이어야 합니다.");
		}
		if (partyTO.getProductPrice() <= 0) {
			throw new IllegalArgumentException("상품단가는 0보다 커야 합니다.");
		}
		if (partyTO.getPartyEndDateTime() == null || partyTO.getPartyEndDateTime().isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("모집마감일시는 현재 이후여야 합니다.");
		}
		
		dao.createParty(partyTO);
	}

	public void deleteParty(int partyNo, int userNo) {

		PartyTO party = getPartyDetail(partyNo);
		
		if (party.getUserNo() != userNo) {
			throw new SecurityException("본인이 작성한 글만 삭제할 수 있습니다.");
		}

		dao.deleteParty(partyNo);

	}
	
	public List<PartyTO> getPartyListByUserNo(int userNo) {
		return dao.getPartyListByUserNo(userNo);
	}
}