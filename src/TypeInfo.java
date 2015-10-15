// TypeInfo.java

import java.net.URL;
import java.sql.*;

public class TypeInfo {

	public static void main(String args[]) {

		// 連線參數
		// String url =
		// "jdbc:sqlserver://localhost:1433;databaseName=XE;user=hr;password=hr;";
		String url = "jdbc:mysql://localhost:3306/XE";
		Connection con;
		DatabaseMetaData dbmd;

		/*
		 * try { Class.forName("myDriver.ClassName");
		 * 
		 * } catch(java.lang.ClassNotFoundException e) {
		 * System.err.print("ClassNotFoundException: ");
		 * System.err.println(e.getMessage()); }
		 */

		try {
			con = DriverManager.getConnection(url,"root","kent1011");

			dbmd = con.getMetaData();

			ResultSet rs = dbmd.getTypeInfo();
			while (rs.next()) {
				String typeName = rs.getString("TYPE_NAME");
				short dataType = rs.getShort("DATA_TYPE");
				String createParams = rs.getString("CREATE_PARAMS");
				int nullable = rs.getInt("NULLABLE");
				boolean caseSensitive = rs.getBoolean("CASE_SENSITIVE");
				System.out.println("DBMS type " + typeName + ":");
				System.out.println("     java.sql.Types:  " + dataType);
				System.out.print("     parameters used to create: ");
				System.out.println(createParams);
				System.out.println("     nullable?:  " + nullable);
				System.out.print("     case sensitive?:  ");
				System.out.println(caseSensitive);
				System.out.println("");

			}

			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
}
