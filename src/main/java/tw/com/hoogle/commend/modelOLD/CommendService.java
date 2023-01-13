package tw.com.hoogle.commend.modelOLD;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class CommendService {

	private CommendDAO_interface dao;

	public CommendService() {

		dao = new CommendDAO();
	}

	public CommendVO addCommend(Integer commendAuto, Integer ordId, Integer commendGrade, String commendContent, Date commendDate) {

		CommendVO CommendVO = new CommendVO();

		CommendVO.setCommendAuto(commendAuto);
		CommendVO.setOrdId(ordId);
		CommendVO.setCommendGrade(commendGrade);
		CommendVO.setCommendContent(commendContent);
		CommendVO.setCommendDate(commendDate);
		dao.insert(CommendVO);

		return CommendVO;
	}

	// 預留給 Struts 2 或 Spring MVC 用
	public void addCommend(CommendVO CommendVO) {
		dao.insert(CommendVO);
	}

	public CommendVO updateCommend(Integer commendAuto, Integer orderId, Integer commendGrade, String commendContent, java.sql.Date commendDate) {

		CommendVO CommendVO = new CommendVO();

		CommendVO.setCommendAuto(commendAuto);
		CommendVO.setOrdId(orderId);
		CommendVO.setCommendGrade(commendGrade);
		CommendVO.setCommendContent(commendContent);
		CommendVO.setCommendDate(commendDate);

		return dao.findByPrimaryKey(commendAuto);
	}

	// 預留給 Struts 2 用的
	public void updateCommend(CommendVO CommendVO) {
		dao.update(CommendVO);
	}
	
	public void deleteCommend(Integer commendAuto) {
		dao.delete(commendAuto);
	}
	
	public CommendVO getOneCommend(Integer commendAuto) {
		return dao.findByPrimaryKey(commendAuto);
	}
	
	public List<CommendVO> getAll(){
		return dao.getAll();
	}

}
