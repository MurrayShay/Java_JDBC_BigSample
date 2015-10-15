// DataType.java
// DatabaseMetaData¨Ï¥Î½d¨Ò

public class DataType {

	private int code;
	private String SQLType;
	private String localType = null;
	private String params = null;
	private boolean needsSetting = true; 
	
	public DataType(int code, String SQLType) {
		this.code = code;
		this.SQLType = SQLType;
	
	}
	
	public boolean needsToBeSet() {
		return needsSetting;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getSQLType() {
		return SQLType;
	}
	
	public String getLocalType() {
		return localType;
	}
	
	public String getParams() {
		return params;
	}
	
	public void setLocalTypeAndParams(String local, String p) {
		if (needsSetting) {
			localType = local;
			params = p;
			needsSetting = false;
		}
	}
}

