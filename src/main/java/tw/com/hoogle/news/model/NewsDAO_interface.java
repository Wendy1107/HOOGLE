package tw.com.hoogle.news.model;

import java.sql.Date;
import java.util.List;

public interface NewsDAO_interface {
	
	public void insert(NewsVO newsVO);
	public void update(NewsVO newsVO);
	public List<NewsVO> getAll();
	public List<NewsVO> findByDate(Date dateFrom, Date dateEnd);
//	public NewsVO findByPrimaryKey(Integer newsId);
	public void disable(NewsVO newsVO);
	public List<NewsVO> showIndex();
}
