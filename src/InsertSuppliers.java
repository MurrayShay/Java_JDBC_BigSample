// InsertSuppliers.java
import java.sql.*;

public class InsertSuppliers {

	public static void main(String args[]) {
		// 連線參數
		// String url =
		// "jdbc:sqlserver://localhost:1433;databaseName=XE;user=hr;password=hr;";
		String url = "jdbc:mysql://localhost:3306/XE";

		Connection con;
		Statement stmt;
		String query = "select SUP_NAME, SUP_ID from SUPPLIERS";

		try {
			con = DriverManager.getConnection(url,"root","kent1011");

			stmt = con.createStatement();

			stmt.executeUpdate("insert into SUPPLIERS "
					+ "values(49, 'Superior Coffee', '1 Party Place', "
					+ "'Mendocino', 'CA', '95460')");

			stmt.executeUpdate("insert into SUPPLIERS "
					+ "values(101, 'Acme, Inc.', '99 Market Street', "
					+ "'Groundsville', 'CA', '95199')");

			stmt.executeUpdate("insert into SUPPLIERS "
					+ "values(150, 'The High Ground', '100 Coffee Lane', "
					+ "'Meadows', 'CA', '93966')");

			ResultSet rs = stmt.executeQuery(query);

			System.out.println("Suppliers and their ID Numbers:");
			while (rs.next()) {
				String s = rs.getString("SUP_NAME");
				int n = rs.getInt("SUP_ID");
				System.out.println(s + "   " + n);
			}

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
}
