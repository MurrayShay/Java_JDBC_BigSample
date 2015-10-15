// Join.java
import java.sql.*;

public class Join {
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
			System.out.println("Supplier, Coffee:");
			while (rs.next()) {
				String supName = rs.getString(1);
				String cofName = rs.getString(2);
				System.out.println("    " + supName + ", " + cofName);
			}

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}
	}
}
