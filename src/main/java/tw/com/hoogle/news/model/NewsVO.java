package tw.com.hoogle.news.model;

import java.io.Serializable;
import java.sql.Date;

public class NewsVO implements Serializable {
	private Integer newsId;				// 最新消息編號
	private Integer administratorId;	// 管理者編號
	private String newsSubject;			// 最新消息主旨
	private String newsContent;			// 最新消息內容
	private Date newsDate;				// 上架時間
	private byte[] newsPic;				// 最新消息照片
	private Integer newsState;				// 最新消息狀態
	
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public Integer getAdministratorId() {
		return administratorId;
	}
	public void setAdministratorId(Integer administratorId) {
		this.administratorId = administratorId;
	}
	public String getNewsSubject() {
		return newsSubject;
	}
	public void setNewsSubject(String newsSubject) {
		this.newsSubject = newsSubject;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public Date getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}
	public byte[] getNewsPic() {
		return newsPic;
	}
	public void setNewsPic(byte[] newsPic) {
		this.newsPic = newsPic;
	}
	public Integer getNewsState() {
		return newsState;
	}
	public void setNewsState(int newsState) {
		this.newsState = newsState;
	}
	
}
