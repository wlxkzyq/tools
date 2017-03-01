package tools.mygenerator.api;

import java.util.List;
import java.util.Set;

/** 
* 数据库表信息
* @author 作者 : zyq
* 创建时间：2016年12月17日 下午3:16:10 
* @version 
*/
public class IntrospectedTable {
	
	private String tableCatalog;
	private String tableSchema;
	private String tableName;
	private String tableType;
	private String remarks;
	
	private List<IntrospectedColumn> columns;
	private Set<IntrospectedPrimaryKey> primaryKeys;
	private Set<IntrospectedForeignKey> foreignKeys;
	
	
	
	//show create TABLE url_info 
	public void init(String tableCatalog,String tableSchema,String tableName){
		this.tableCatalog=tableCatalog;
		this.tableSchema=tableSchema;
		this.tableName=tableName;
	}



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
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<IntrospectedColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<IntrospectedColumn> columns) {
		this.columns = columns;
	}
	public Set<IntrospectedPrimaryKey> getPrimaryKeys() {
		return primaryKeys;
	}
	public void setPrimaryKeys(Set<IntrospectedPrimaryKey> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}
	public Set<IntrospectedForeignKey> getForeignKeys() {
		return foreignKeys;
	}
	public void setForeignKeys(Set<IntrospectedForeignKey> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

}
