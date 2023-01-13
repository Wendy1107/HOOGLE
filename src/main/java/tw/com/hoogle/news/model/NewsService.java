package tw.com.hoogle.news.model;

import java.sql.Date;
import java.util.List;

import tw.com.hoogle.administrator.model.AdministratorVO;

public class NewsService {
	
	private NewsDAO_interface dao;
	
	public NewsService() {
		dao = new NewsDAO();		
	}
	
	public NewsVO addNews(Integer administratorId, String newsSubject, String newsContent, 
			Date newsDate, byte[] newsPic, Integer newsState) {
		
		NewsVO newsVO = new NewsVO();
		
		newsVO.setAdministratorId(administratorId);
		newsVO.setNewsSubject(newsSubject);
		newsVO.setNewsContent(newsContent);
		newsVO.setNewsDate(newsDate);
		newsVO.setNewsPic(newsPic);
		newsVO.setNewsState(newsState);
		dao.insert(newsVO);
		
		return newsVO;		
	}
	
	public NewsVO updateNews(Integer newsId, Integer administratorId, String newsSubject, 
			String newsContent, Date newsDate, byte[] newsPic, Integer newsState) {
		
		NewsVO newsVO = new NewsVO();
		
		newsVO.setNewsId(newsId);
		newsVO.setAdministratorId(administratorId);
		newsVO.setNewsSubject(newsSubject);
		newsVO.setNewsContent(newsContent);
		newsVO.setNewsDate(newsDate);
		newsVO.setNewsPic(newsPic);
		newsVO.setNewsState(newsState);
		dao.update(newsVO);
		
		return newsVO;
	}
	
	public List<NewsVO> getAll(){
		return dao.getAll();
	}
	
		
	public List<NewsVO> findNewsByDate(Date dateFrom, Date dateEnd){
		return dao.findByDate(dateFrom, dateEnd);
	}
	
	public NewsVO disableNews(Integer newsId) {
		NewsVO newsVO = new NewsVO();
		
		newsVO.setNewsId(newsId);
		dao.disable(newsVO);

		return newsVO;
	}
	
	public List<NewsVO> showOnIndex(){
		return dao.showIndex();
	}

}
