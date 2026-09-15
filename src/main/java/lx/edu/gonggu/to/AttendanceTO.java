package lx.edu.gonggu.to;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AttendanceTO {

	private int attNo;
	private int attQty;
	private LocalDateTime attDateTime;
	private String attStatus;
	private int partyNo;
	private int userNo;
	private String userName;
}
