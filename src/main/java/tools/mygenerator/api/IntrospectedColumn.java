package tools.mygenerator.api;
/** 
* 数据库列信息
* @author 作者 : zyq
* 创建时间：2016年12月17日 上午11:37:14 
* @version 
*/
public class IntrospectedColumn {
	
	private String tableCatalog;
	private String tableSchema;
	private String tableName;
	private String columnName;
	private int dataType;
	private String typeName;
	private String defaultValue;
	/**
	 * 总长度，包括小数点
	 */
	private int columnSize;
	/**
	 * 是否可空
	 * 0 : 不可空
	 * 1 : 可空
	 */
	private int nullable;
	/**
	 * 小数长度
	 */
	private int decimalDigits;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 是不是增长类型
	 * YES : 是
	 * NO  : 不是
	 */
	private String isAutoIncrement;
	
	/**
	 * 是否主键
	 */
	private boolean primaryKey;
	
	/**
	 * 外键字段
	 */
	private String foreignKey;
	
	public String getTableCatalog() {
		return tableCatalog;
	}
	public void setTableCatalog(String tableCatalog) {
		this.tableCatalog = tableCatalog;
	}
	public String getTableSchema() {
		return tableSchema;
	}
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getColumnSize() {
		return columnSize;
	}
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}
	public int getNullable() {
		return nullable;
	}
	public void setNullable(int nullable) {
		this.nullable = nullable;
	}
	public int getDecimalDigits() {
		return decimalDigits;
	}
	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsAutoIncrement() {
		return isAutoIncrement;
	}
	public void setIsAutoIncrement(String isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getForeignKey() {
		return foreignKey;
	}
	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}
	
	
	

}
