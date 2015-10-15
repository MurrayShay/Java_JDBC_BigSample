// SQLSatement.java
// ResultSetMetaData使用範例

import java.sql.*;

public class SQLStatement {

	public static void main(String args[]) {

		// 連線參數
		// String url =
		// "jdbc:sqlserver://localhost:1433;databaseName=XE;user=hr;password=hr;";
		String url = "jdbc:mysql://localhost:3306/XE";
		Connection con;
		String query = "select SUPPLIERS.SUP_NAME, COFFEES.COF_NAME "
				+ "from COFFEES, SUPPLIERS "
				+ "where SUPPLIERS.SUP_NAME like 'Acme, Inc.' and "
				+ "SUPPLIERS.SUP_ID = COFFEES.SUP_ID";
		Statement stmt;

		try {
			con = DriverManager.getConnection(url,"root","kent1011");

			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			int rowCount = 1;
			while (rs.next()) {
				System.out.println("Row " + rowCount + ":  ");
				for (int i = 1; i <= numberOfColumns; i++) {
					System.out.print("   Column " + i + ":  ");
					System.out.println(rs.getString(i));
				}
				System.out.println("");
				rowCount++;
			}
			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}
	}
}
