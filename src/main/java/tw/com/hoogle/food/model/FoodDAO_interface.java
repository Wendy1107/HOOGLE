package tw.com.hoogle.food.model;

import java.util.List;

public interface FoodDAO_interface {
	
	public void insert(FoodVO foodVO);
	public void update(FoodVO foodVO);
	public void delete(Integer foodPicid);
	public FoodVO findByPrimaryKey(Integer foodPicid);
	public List<FoodVO> getAll();
}
