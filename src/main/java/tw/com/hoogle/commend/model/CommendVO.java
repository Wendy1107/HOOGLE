package tw.com.hoogle.commend.model;

import java.sql.Date;

public class CommendVO implements java.io.Serializable {
	private Integer commendAuto;
	private Integer ordId;
	private Integer commendGrade;
	private String commendContent;
	private Date commendDate;
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
