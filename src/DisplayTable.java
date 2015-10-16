// DisplayTable.java
// 本程式功能:以Swing方式顯示表格內容

import java.sql.*;

import javax.swing.*;

import com.mysql.jdbc.Driver;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

//import oracle.jdbc.*;

public class DisplayTable extends JFrame {
	private Connection connection;
	private JTable table;

	public DisplayTable() throws Exception {
		// String DBUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		// 連線參數
		String url = "jdbc:mysql://localhost:3306/XE";
		// 宣告JDBC相關物件
		try {
			Driver driver = (Driver) DriverManager.getDriver(url);
			Class.forName(driver.getClass().getName()).newInstance();
			DriverManager.setLoginTimeout(60);
			// Class.forName("org.gjt.mm.mysql.Driver").newInstance(); or
			// DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// 建立資料庫連線
			connection = DriverManager.getConnection(url, "root", "kent1011");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		getTable();
		setSize(450, 150); // 設定JFrame視窗大小
		// show();
		setVisible(true);
	}

	private void getTable() {
		Statement statement;
		ResultSet resultSet;

		try {
			String query = "SELECT * FROM employees";

			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			displayResultSet(resultSet); // 顯示查詢結果
			statement.close();
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}

	private void displayResultSet(ResultSet rs) throws SQLException {
		boolean moreRecords = rs.next(); // 抓取第一筆記錄

		// 若無紀錄
		if (!moreRecords) {
			JOptionPane.showMessageDialog(this, "無資料");
			setTitle("未發現任何資料");
			return;
		}

		setTitle("員工資料表");

		Vector columnHeads = new Vector();
		Vector rows = new Vector(); // 記錄存入vector

		try {
			// 取欄位
			ResultSetMetaData rsmd = rs.getMetaData(); // 取得表格定義資訊
			for (int i = 1; i <= rsmd.getColumnCount(); ++i)
				columnHeads.addElement(rsmd.getColumnName(i)); // 取得欄位名稱

			// 取下筆記錄資料
			do {
				rows.addElement(getNextRow(rs, rsmd));
			} while (rs.next());

			// 顯示表格
			table = new JTable(rows, columnHeads);
			JScrollPane scroller = new JScrollPane(table);
			getContentPane().add(scroller, BorderLayout.CENTER);
			validate();
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}

	private Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd)
			throws SQLException {
		Vector currentRow = new Vector();

		for (int i = 1; i <= rsmd.getColumnCount(); ++i)
			switch (rsmd.getColumnType(i)) {
			case Types.VARCHAR:
				currentRow.addElement(rs.getString(i));
				break;
			case Types.NCHAR:
				currentRow.addElement(rs.getString(i));
				break;
			case Types.INTEGER:
				currentRow.addElement(new Long(rs.getLong(i)));
				break;
			case Types.FLOAT:
				currentRow.addElement(new Float(rs.getFloat(i)));
				break;
			case Types.NUMERIC:
				currentRow.addElement(new Long(rs.getLong(i)));
				break;
			case Types.DATE:
				currentRow.addElement(rs.getDate(i));
				break;
			case 93: // Oracle DATe Types Value is 93 but JDBC's DATE Value is
						// 91
				currentRow.addElement(rs.getDate(i));
				break;
			default:
				System.out.println("Type was: " + rsmd.getColumnTypeName(i));
				// rsmd.getColumnType( i ) +"    "+Types.DATE);
			}

		return currentRow;
	}

	public void shutDown() {
		try {
			connection.close();
		} catch (SQLException sqlex) {
			System.err.println("無法連線");
			sqlex.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {
		DisplayTable app = new DisplayTable();

		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}