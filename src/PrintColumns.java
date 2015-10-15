// PrintColumns.java
import java.sql.*;

class PrintColumns {

	public static void main(String args[]) {

		// 連線參數
		// String url =
		// "jdbc:sqlserver://localhost:1433;databaseName=XE;user=hr;password=hr;";
		String url = "jdbc:mysql://localhost:3306/XE";
		Connection con;
		String query = "select * from COFFEES";
		Statement stmt;

		/*
		 * try { Class.forName("myDriver.ClassName");
		 * 
		 * } catch(java.lang.ClassNotFoundException e) {
		 * System.err.print("ClassNotFoundException: ");
		 * System.err.println(e.getMessage()); }
		 */

		try {
			con = DriverManager.getConnection(url, "root", "kent1011");

			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			PrintColumnTypes.printColTypes(rsmd);
			System.out.println("");

			int numberOfColumns = rsmd.getColumnCount();

			for (int i = 1; i <= numberOfColumns; i++) {
				if (i > 1)
					System.out.print(",  ");
				String columnName = rsmd.getColumnName(i);
				System.out.print(columnName);
			}
			System.out.println("");

			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					if (i > 1)
						System.out.print(",  ");
					String columnValue = rs.getString(i);
					System.out.print(columnValue);
				}
				System.out.println("");
			}

			stmt.close();
			con.close();
		} catch (SQLException ex) {
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}
	}
}
