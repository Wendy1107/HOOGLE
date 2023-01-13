package tw.com.hoogle.search.model;

import java.util.List;

public interface SearchDAO {
	
	public abstract SearchBean select(Integer userId);

//	public abstract List<SearchBean> select();

	
}
