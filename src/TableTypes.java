// TableTypes.java
// DatabaseMetaData使用範例

import java.sql.*;

public class TableTypes {

	public static void main(String args[]) {

		// 連線參數
		// String url =
		// "jdbc:sqlserver://localhost:1433;databaseName=XE;user=hr;password=hr;";
		String url = "jdbc:mysql://localhost:3306/XE";
		Connection con;

		try {
			con = DriverManager.getConnection(url,"root","kent1011");

			DatabaseMetaData dbmd = con.getMetaData();
			String dbmsName = dbmd.getDatabaseProductName(); // 資料庫產品
			ResultSet rs = dbmd.getTableTypes();
			System.out.print("The following types of tables are ");
			System.out.println("available in " + dbmsName + ":  ");

			while (rs.next()) {
				String tableType = rs.getString("TABLE_TYPE");
				System.out.println("    " + tableType);
			}

			rs.close();
			con.close();

		} catch (SQLException ex) {
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}
	}
}
