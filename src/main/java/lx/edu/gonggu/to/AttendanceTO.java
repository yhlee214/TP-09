package lx.edu.gonggu.to;

import java.sql.Date;

import lombok.Data;

@Data
public class AttendanceTO {

	private int attNo;
	private int attQty;
	private Date attDateTime;
	private String attStatus;
	private int partyNo;
	private int userNo;
}
