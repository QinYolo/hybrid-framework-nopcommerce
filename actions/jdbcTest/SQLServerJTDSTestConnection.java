package jdbcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLServerJTDSTestConnection {
	
	public static Connection getMyConnection() throws SQLException, ClassNotFoundException{
		return SQLServerJTDSConnUtils.getSQLServerConnection();
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("Get connection...");
		
		// Lấy ra đối tượng Connection kết nối vào database
		Connection conn = SQLServerJTDSTestConnection.getMyConnection();
		System.out.println("Openned connection:" + conn);
		Statement statement = conn.createStatement();
//		String sql = "SELECT * FROM [autotesting].[dbo].[Product_Type];";
		
//		String sql = "SELECT * FROM [autotesting].[dbo].[BRANCH];";
//		String insertValue = "INSERT INTO [dbo].[BRANCH] ([ADDRESS],[CITY],[NAME],[STATE],[ZIP_CODE]) "
//				+ "VALUES ('25 Le Loi','Da Nang','Automation','DN','55000')";
//		int rowCount = statement.executeUpdate(insertValue);
//		System.out.println("Row count affected= " + rowCount);
		
		String sqlParam = "Select Emp_Id, First_Name, Title, Dept_Id from [autotesting].[dbo].[EMPLOYEE]"
				+ " where Title like ? and Dept_Id = ?";
		PreparedStatement pstm = conn.prepareStatement(sqlParam);
		pstm.setString(1, "%ent");
		pstm.setInt(2, 3);
		
		// Thực thi câu lệnh SQL trả về đối tượng ResultSet
//		ResultSet rs = statement.executeQuery(sql);
		
		ResultSet rs = pstm.executeQuery();
		
		// Duyệt trên kết quả trả về 
		while (rs.next()) {
			// Di chuyển con trỏ xuống bản ghi kế tiếp
//			String empFirstName = rs.getString(1);
//			String empLastName = rs.getString("NAME");
//			
//			System.out.println("---------------------");
//			System.out.println("Emp Firstname:" + empFirstName);
//			System.out.println("Emp Lastname:" + empLastName);
			
//			System.out.println("---------------------");
//			System.out.println(rs.getInt(1));
//			System.out.println(rs.getString(2));
//			System.out.println(rs.getString(3));
//			System.out.println(rs.getString(4));
//			System.out.println(rs.getString(5));
			
			System.out.println("---------------------");
			System.out.println("Emp Id: " + rs.getInt("Emp_Id"));
			System.out.println("Emp FirstName: " + rs.getString("First_Name"));
			System.out.println("Emp Title: " + rs.getString("Title"));
			System.out.println("Emp Department: " + rs.getString("Dept_Id"));
		}
		// Đóng kết nối
		conn.close();
		System.out.println("--------------Closed connection--------------");
	}

}
