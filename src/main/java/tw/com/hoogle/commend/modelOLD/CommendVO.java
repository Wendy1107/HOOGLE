package tw.com.hoogle.commend.modelOLD;

import java.sql.Date;

public class CommendVO implements java.io.Serializable{
	
	private Integer commendAuto;// (評價流水編號): int, not null
	private Integer ordId;//(訂單編號): int, null
	private Integer commendGrade;//(評價等級): int, null
	private String commendContent;//(評價內容): varchar(500), null
	private Date commendDate;//(評價日期): date null
	
	public Integer getCommendAuto() {
		return commendAuto;
	}
	public void setCommendAuto(Integer commendAuto) {
		this.commendAuto = commendAuto;
	}
	public Integer getOrdId() {
		return ordId;
	}
	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}
	public Integer getCommendGrade() {
		return commendGrade;
	}
	public void setCommendGrade(Integer commendGrade) {
		this.commendGrade = commendGrade;
	}
	public String getCommendContent() {
		return commendContent;
	}
	public void setCommendContent(String commendContent) {
		this.commendContent = commendContent;
	}
	public Date getCommendDate() {
		return commendDate;
	}
	public void setCommendDate(Date commendDate) {
		this.commendDate = commendDate;
	}
	
	
	
}
