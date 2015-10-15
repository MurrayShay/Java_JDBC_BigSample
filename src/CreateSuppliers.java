//CreateSuppliers.java
//�إߨ����Ӫ��
import java.net.URL;
import java.sql.*;

public class CreateSuppliers {

	public static void main(String args[]) {
		// �s�u�Ѽ�
		// String url =
		// "jdbc:sqlserver://localhost:1433;databaseName=XE;user=hr;password=hr;";
		String url = "jdbc:mysql://localhost:3306/XE";
		String createString; // �إߪ����O
		createString = "create table SUPPLIERS " + "(SUP_ID int, "
				+ "SUP_NAME varchar(40), " + "STREET varchar(40), "
				+ "CITY varchar(20), " + "STATE char(2), ZIP char(5))";
		Statement stmt;
		try {
			Connection conn = DriverManager.getConnection(url, "root",
					"kent1011");
			stmt = conn.createStatement();
			stmt.executeUpdate(createString);
			stmt.close();
			conn.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
}
