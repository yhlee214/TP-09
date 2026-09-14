package lx.edu.gonggu.to;

import java.sql.Date;

import lombok.Data;

@Data
public class ReplyTO {

	private int replyNo;
	private String replyContent;
	private Date replyDateTime;
	private int partyNo;
	private int userNo;
}
