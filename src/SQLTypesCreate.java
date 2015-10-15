// SQLTyoesCreate.java
// DatabaseMetaData使用範例

import java.sql.*;
     
public class SQLTypesCreate {

	public static void main(String [] args) {
		// 連線參數
		// String url =
		// "jdbc:sqlserver://localhost:1433;databaseName=XE;user=hr;password=hr;";
		String url = "jdbc:mysql://localhost:3306/XE";
		Connection con;
		Statement stmt;
	
		try {
			con = DriverManager.getConnection(url,"root","kent1011");
			stmt = con.createStatement();
			String tableName;
			String columnName;
			String sqlType;
	
		// create an array of class DataType initialized with
		// the SQL code, the SQL type name, and two null entries
		// for the local type name and the creation parameter(s)
	
		DataType [] typeArray = {
			new DataType(java.sql.Types.BIT, "BIT"),
			new DataType(java.sql.Types.TINYINT, "TINYINT"),
			new DataType(java.sql.Types.SMALLINT, "SMALLINT"),
			new DataType(java.sql.Types.INTEGER, "INTEGER"),
			new DataType(java.sql.Types.BIGINT, "BIGINT"),
			new DataType(java.sql.Types.FLOAT, "FLOAT"),
			new DataType(java.sql.Types.REAL, "REAL"),
			new DataType(java.sql.Types.DOUBLE, "DOUBLE"),
			new DataType(java.sql.Types.NUMERIC, "NUMERIC"),
			new DataType(java.sql.Types.DECIMAL, "DECIMAL"),
			new DataType(java.sql.Types.CHAR, "CHAR"),
			new DataType(java.sql.Types.VARCHAR, "VARCHAR"),
			new DataType(java.sql.Types.LONGVARCHAR, "LONGVARCHAR"),
			new DataType(java.sql.Types.DATE, "DATE"),
			new DataType(java.sql.Types.TIME,"TIME"),
			new DataType(java.sql.Types.TIMESTAMP, "TIMESTAMP"),
			new DataType(java.sql.Types.BINARY, "BINARY"),
			new DataType(java.sql.Types.VARBINARY, "VARBINARY"),
			new DataType(java.sql.Types.LONGVARBINARY, "LONGVARBINARY"),
			new DataType(java.sql.Types.NULL, "NULL"),
			new DataType(java.sql.Types.OTHER, "OTHER"),
			};
	
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet rs = dbmd.getTypeInfo();
			while (rs.next()) {
				int codeNumber = rs.getInt("DATA_TYPE");
				String dbmsName = rs.getString("TYPE_NAME");
				String createParams = rs.getString("CREATE_PARAMS");
	
				// find entry that matches the SQL code, 
				// and if local type and params are not already set,
				// set them
				for (int i = 0; i < typeArray.length; i++) {
					if (typeArray[i].getCode() == codeNumber) {
						typeArray[i].setLocalTypeAndParams(
											dbmsName, createParams);
						
					}
				}
			}
			
			String tableNamePrompt = "Enter the table name " + 
									"and hit Return: ";
			tableName = getInput(tableNamePrompt);
			String createTableString = "create table " +
									tableName + " (";
			
			String commaAndSpace = ", ";
			boolean firstTime = true;
			while (true){
				System.out.println("");
				String columnNamePrompt = "Enter a column name " + 
					   	"(or nothing when finished) \nand hit Return: ";
				columnName = getInput(columnNamePrompt);
				if (firstTime) {
					if (columnName.length() == 0) {
						System.out.print("Need at least one column;");
						System.out.println(" please try again");
						continue;
					} else {
						createTableString += columnName + " ";
						firstTime = false;
					}
				} else if (columnName.length() == 0) {
						break;
				} else {
					createTableString += commaAndSpace
						+ columnName + " "; 
				}
	
				System.out.println("");
				System.out.println("LIST OF TYPES YOU MAY USE:  ");
				for (int i = 0; i < typeArray.length; i++) {
					if (! typeArray[i].needsToBeSet()) {
						System.out.println(typeArray[i].getSQLType());
					}	
				}
				System.out.println("");
	
				int index;
				while (true) {
					String typePrompt = "Enter a column type " + 
							"from the list and hit Return:  ";
					sqlType = getInput(typePrompt);
					for (index = 0; index < typeArray.length; index++) {
						if (typeArray[index].getSQLType().equalsIgnoreCase(
														sqlType)){
							break;
						}
					}
	
					if (index < typeArray.length) { // there was a match
						break;
					}
					System.out.println("");
					System.out.print(sqlType + " does not match ");
					System.out.println("available types.");
					System.out.println("");
				}
				
				String params;
				String localTypeName;
				params = typeArray[index].getParams();
				localTypeName = typeArray[index].getLocalType();
				String paramString;
				if (params != null) {
					String parameterPrompt = "Enter " + params + ":  ";
					paramString = "(" + getInput(parameterPrompt) + ")";
				} else {
					paramString = "";
				}
				createTableString += localTypeName + paramString;
			}
	
			createTableString += ")";
			System.out.println("");
			System.out.print("Your CREATE TABLE statement as ");
			System.out.println("sent to your DBMS:  ");
			System.out.println(createTableString);
			System.out.println("");
	
	   		stmt.executeUpdate(createTableString);
	
			stmt.close();
			con.close();
	
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}	
	}

	public static String getInput(String prompt) throws SQLException {
 
		 System.out.print(prompt);
		 System.out.flush();
	
		 try {
			 java.io.BufferedReader bin;
			 bin = new java.io.BufferedReader(
					new java.io.InputStreamReader(System.in));
										  
   		  	String result = bin.readLine();
			return result;

		} catch(java.io.IOException ex) {
			System.out.println("Caught java.io.IOException:");
			System.out.println(ex.getMessage());
			return "";
		}
	}
}
