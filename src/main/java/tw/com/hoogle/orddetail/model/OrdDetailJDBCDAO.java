package tw.com.hoogle.orddetail.model;

public class OrdDetailJDBCDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/HOOGLE?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

//		private static DataSource ds = null;
//		static {
//			try {			
//				Context ctx = new InitialContext();
//				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HOOGLEDB");
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//		}

		private static final String INSERT_STMT = 
				"INSERT INTO orddetail (ordId, roomAuto, roomNumber) VALUES (?, ?, ?)";
			private static final String GET_ALL_STMT = 
				"SELECT orddetailId, ordId, roomAuto, roomNumber FROM orddetail order by orddetailId";
			private static final String GET_ONE_STMT = 
				"SELECT orddetailId, ordId, roomAuto, roomNumber FROM orddetail where orddetailId = ?";
			private static final String GET_ORD_STMT =
				"SELECT orddetailId, ordId, roomAuto, roomNumber FROM orddetail where ordId = ?";
			private static final String DELETE = 
				"DELETE FROM orddetail where orddetailId = ?";
			private static final String UPDATE = 
				"UPDATE orddetail set ordId=?, roomAuto=?, roomNumber=? where orddetailId=?";

			
	public static void main(String[] args) {
		
				OrdDetailDAO dao = new OrdDetailDAO();
		
				// 新增
//				OrdDetailVO orddetailVO1 = new OrdDetailVO();
//				orddetailVO1.setOrdId(1);
//				orddetailVO1.setRoomAuto(3001);
//				orddetailVO1.setRoomNumber(1);
//				dao.insert(orddetailVO1);
//				System.out.println("新增成功");
//				System.out.println("---------------------");
				
				//訂單查詢
//				OrdDetailVO orddetailVO4 = dao.findByOrdId(5002);
//				System.out.print(orddetailVO4.getOrddetailId() + ",");
//				System.out.println(orddetailVO4.getOrdId()+",");
//				System.out.print(orddetailVO4.getRoomAuto() + ",");
//				System.out.print(orddetailVO4.getRoomNumber() + ",");
//				System.out.println("查詢成功");
//				System.out.println("---------------------");
				
				//查詢單筆
//				OrdDetailVO orddetailVO2 = dao.findByPrimaryKey(2);
//				System.out.print(orddetailVO2.getOrddetailId() + ",");
//				System.out.println(orddetailVO2.getOrdId()+",");
//				System.out.print(orddetailVO2.getRoomAuto() + ",");
//				System.out.print(orddetailVO2.getRoomNumber() + ",");
//				System.out.println("查詢成功");
//				System.out.println("---------------------");
				
				//查詢全部
//				List<OrdDetailVO> list = dao.getAll();
//				for (OrdDetailVO aOrdDetail : list) {
//				System.out.print(aOrdDetail.getOrddetailId() + ",");
//				System.out.print(aOrdDetail.getOrdId() + ",");
//				System.out.print(aOrdDetail.getRoomAuto() + ",");
//				System.out.print(aOrdDetail.getRoomNumber() + ",");
//				System.out.println("查詢成功");
//				System.out.println("---------------------");
//				}
				
				//刪除
//				dao.delete(6010);
//				System.out.println("刪除成功");
//				System.out.println("---------------------");
				
				//修改
//				OrdDetailVO orddetailVO3 = new OrdDetailVO();
//				orddetailVO3.setOrddetailId(1);
//				orddetailVO3.setOrdId(1);
//				orddetailVO3.setRoomAuto(3002);
//				orddetailVO3.setRoomNumber(123);
//				dao.update(orddetailVO3);
//				System.out.println("修改成功");
//				System.out.println("---------------------");
				
				
	}
}
