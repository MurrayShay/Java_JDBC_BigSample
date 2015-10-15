// CreateCoffees.java
import java.sql.*;

import com.mysql.jdbc.Driver;

public class CreateCoffees {
	public static void main(String args[]) {
		// 連線參數
		// String url =
		// "jdbc:sqlserver://localhost:1433;databaseName=XE;user=hr;password=hr;";
		String url = "jdbc:mysql://localhost:3306/XE";

		Connection con;
		String createString;
		createString = "create table COFFEES " + "(COF_NAME varchar(32), "
				+ "SUP_ID int, " + "PRICE float, " + "SALES int, "
				+ "TOTAL int)";
		Statement stmt;

		try {
			// 產生JDBC驅動程式物件
			Driver driver = (Driver) DriverManager.getDriver(url);
			Class.forName(driver.getClass().getName()).newInstance();
			// 建立資料庫連線
			con = DriverManager.getConnection(url, "root", "kent1011");
			// 產生並執行 SQL指令物件.
			stmt = con.createStatement();
			stmt.executeUpdate(createString);

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}