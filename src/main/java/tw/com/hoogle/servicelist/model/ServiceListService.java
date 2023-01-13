package tw.com.hoogle.servicelist.model;

import java.util.List;

import tw.com.hoogle.ord.model.OrdDAO;

public class ServiceListService {	

		private ServiceListDAO_interface dao;

		public ServiceListService() {
			dao = new ServiceListDAO();
		}

		public ServiceListVO addServiceList(Integer hotelId,Integer serviceId) {

			ServiceListVO servicelistVO = new ServiceListVO();
			
			servicelistVO.setHotelId(hotelId);
			servicelistVO.setServiceId(serviceId);
			dao.insert(servicelistVO);

			return servicelistVO;
		}

		

		public ServiceListVO updateServiceList(Integer serviceListId, Integer hotelId,Integer serviceId) {


			ServiceListVO servicelistVO = new ServiceListVO();
			
			servicelistVO.setServiceListId(serviceListId);
			servicelistVO.setHotelId(hotelId);
			servicelistVO.setServiceId(serviceId);
			dao.update(servicelistVO);
			return servicelistVO;
			
		}
		public void deleteServiceList(Integer serviceListId) {
			dao.delete(serviceListId);
		}

		public ServiceListVO getOneServiceList(Integer serviceListId) {
			return dao.findByPrimaryKey(serviceListId);
		}

		public List<ServiceListVO> getAll() {
			return dao.getAll();
		}
	

		// 預留給 Struts 2 用的
		public void updateServiceListVO(ServiceListVO servicelistVO) {
			dao.update(servicelistVO);
		}
		}
//		
//		public void deleteFood(Integer foodPicid) {
//			dao.delete(foodPicid);
//		}
//		
//		public FoodVO getOneFood(Integer foodPicid) {
//			return dao.findByPrimaryKey(foodPicid);
//		}
//		
//		public List<FoodVO> getAll(){
//			return dao.getAll();
//		}
//
//	}


