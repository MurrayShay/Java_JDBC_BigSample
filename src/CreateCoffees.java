// CreateCoffees.java
import java.sql.*;

import com.mysql.jdbc.Driver;

public class CreateCoffees {
	public static void main(String args[]) {
		// �s�u�Ѽ�
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
			// ����JDBC�X�ʵ{������
			Driver driver = (Driver) DriverManager.getDriver(url);
			Class.forName(driver.getClass().getName()).newInstance();
			// �إ߸�Ʈw�s�u
			con = DriverManager.getConnection(url, "root", "kent1011");
			// ���ͨð��� SQL���O����.
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