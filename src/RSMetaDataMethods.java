// RSMetaDataMethods.java

import java.sql.*;

public class RSMetaDataMethods {

	public static void main(String args[]) {

		// 連線參數
		// String url =
		// "jdbc:sqlserver://localhost:1433;databaseName=XE;user=hr;password=hr;";
		String url = "jdbc:mysql://localhost:3306/XE";
		Connection con;
		Statement stmt;

		/*
		 * try { Class.forName("myDriver.ClassName");
		 * 
		 * } catch(java.lang.ClassNotFoundException e) {
		 * System.err.print("ClassNotFoundException: ");
		 * System.err.println(e.getMessage()); }
		 */

		try {
			con = DriverManager.getConnection(url,"root","kent1011");

			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from COFFEES");
			ResultSetMetaData rsmd = rs.getMetaData();

			int numberOfColumns = rsmd.getColumnCount();
			for (int i = 1; i <= numberOfColumns; i++) {
				String colName = rsmd.getColumnName(i);
				String tableName = rsmd.getTableName(i);
				String name = rsmd.getColumnTypeName(i);
				boolean caseSen = rsmd.isCaseSensitive(i);
				boolean writable = rsmd.isWritable(i);
				System.out.println("Information for column " + colName);
				System.out.println("    Column is in table " + tableName);
				System.out.println("    DBMS name for type is " + name);
				System.out.println("    Is case sensitive:  " + caseSen);
				System.out.println("    Is possibly writable:  " + writable);
				System.out.println("");
			}

			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					String s = rs.getString(i);
					System.out.print(s + "  ");
				}
				System.out.println("");
			}

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
}
