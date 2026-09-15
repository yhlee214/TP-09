package lx.edu.gonggu.to;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReplyTO {

	private int replyNo;
	private String replyContent;
	private LocalDateTime replyDateTime;
	private int partyNo;
	private int userNo;
}
