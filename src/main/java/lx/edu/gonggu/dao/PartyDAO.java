package lx.edu.gonggu.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lx.edu.gonggu.to.PartyTO;


@Repository
@RequiredArgsConstructor
public class PartyDAO {

	private final SqlSession session;
	
	public List<PartyTO> getPartyList () {
		return session.selectList("mapper-party.getPartyList");
	}
	
	public List<PartyTO> getPartyListByUserNo (int userNo) {
		return session.selectList("mapper-party.getPartyListByUserNo", userNo);
	}
	
	public PartyTO getPartyByPartyNo (int partyNo) {
		return session.selectOne("mapper-party.getPartyByPartyNo", partyNo);
	}
	
	public int createParty (PartyTO party) {
		return session.insert("mapper-party.createParty", party);
	}
	
	public int deleteParty (int partyNo) {
		return session.delete("mapper-party.deleteParty", partyNo);
	}
	
}
