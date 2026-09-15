package lx.edu.gonggu.to;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PartyTO {
	
	private int partyNo;
	private String partyTitle;
	private int targetQty;
	private LocalDateTime splitDatetime;
	private String splitAddr;
	private String splitPlace;
	private double longitude;
	private double latitude;
	private String productName;
	private String productContent;
	private int productPrice;
	private String productImg;
	private LocalDateTime partyEndDateTime;
	private int accumQty;
	private String partyStatus;
	private LocalDateTime partyDateTime;
	private LocalDateTime recentUpdate;
	private int maxQty;
	private int userNo;
	private int regionNo;

	
}
