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
		return session.selectList("getPartyList");
	}
	
	public List<PartyTO> getPartyListByUserNo (int userNo) {
		return session.selectList("getPartyListByUserNo", userNo);
	}
	
	public PartyTO getPartyByPartyNo (int partyNo) {
		return session.selectOne("getPartyByPartyNo", partyNo);
	}
	
	public int createParty (PartyTO party) {
		return session.insert("createParty", party);
	}
	
	public int deleteParty (int partyNo) {
		return session.delete("deleteParty", partyNo);
	}
	
}
