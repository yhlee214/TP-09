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
	
	public List<AttendanceTO> getAttListByUserNo(int userNo) {
		return session.selectList("getAttListByUserNo", userNo);
	}
}
