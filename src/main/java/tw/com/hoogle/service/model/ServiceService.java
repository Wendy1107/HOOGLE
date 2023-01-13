package tw.com.hoogle.service.model;

import java.util.*;

import tw.com.hoogle.food.model.FoodVO;



public class ServiceService {

	private ServiceDAO_interface dao;

	public ServiceService() {

		dao = new ServiceDAO();
	}

	public ServiceVO addService( String serviceName) {

		ServiceVO serviceVO = new ServiceVO();

//		serviceVO.setServiceId(serviceId);
		serviceVO.setServiceName(serviceName);
		dao.insert(serviceVO);

		return serviceVO;
	}

	

	public ServiceVO updateService(Integer serviceId, String serviceName) {

		ServiceVO serviceVO = new ServiceVO();

		serviceVO.setServiceId(serviceId);
		serviceVO.setServiceName(serviceName);

		dao.update(serviceVO);
		return serviceVO;
	}

	
	
	public void deleteService(Integer serviceId) {
		dao.delete(serviceId);
	}
	
	
		
		public ServiceVO getOneService(Integer serviceId) {
			return dao.findByPrimaryKey(serviceId);
		}
	

	public List<ServiceVO> getAll() {
		return dao.getAll();
	}

		
	}



	