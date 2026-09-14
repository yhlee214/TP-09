package lx.edu.gonggu.to;

import java.sql.Date;

import lombok.Data;

@Data
public class PartyTO {
	
	private int partyNo;
	private String partyTitle;
	private int targetQty;
	private Date splitDatetime;
	private double longitude;
	private double latitude;
	private String productName;
	private String productContent;
	private int productPrice;
	private String productImg;
	private Date partyEndDateTime;
	private int accumQty;
	private Date partyDateTime;
	private Date recentUpdate;
	private int maxQty;
	private int userNo;
	private int regionNo;

	
}
