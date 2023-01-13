package tw.com.hoogle.administrator.model;

import java.util.HashMap;
import java.util.List;

public interface AdministratorDAO_interface {
	public void insert(AdministratorVO administratorVO);
    public void update(AdministratorVO administratorVO);
    public void delete(Integer administratorId);
    public AdministratorVO findByPrimaryKey(Integer administratorId);
    public List<AdministratorVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<Administrator> getAll(Map<String, String[]> map);
    
    public String findAccount(String administratorAccount);
    public String matchAccountPassword(String administratorAccount);
    public void disable(AdministratorVO administratorVO);
    public AdministratorVO searchPermissionsByAccount(String administratorAccount);

}
