package tw.com.hoogle.service.model;

import java.util.*;

public interface ServiceDAO_interface {

		public void insert(ServiceVO serviceVO);
		public void update(ServiceVO serviceVO);
		public void delete(Integer serviceId);
		public ServiceVO findByPrimaryKey(Integer serviceId);
		public List<ServiceVO> getAll();
}
