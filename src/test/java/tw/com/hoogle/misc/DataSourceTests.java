package tw.com.hoogle.misc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataSourceTests {
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testDataSource() throws Exception {
		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from user");
		ResultSet rset = stmt.executeQuery();
		while(rset.next()) {
			String col1 = rset.getString(1);
			String col2 = rset.getString(2);
			System.out.println(col1+":"+col2);
		}
		rset.close();
		stmt.close();
		conn.close();
	}
}
