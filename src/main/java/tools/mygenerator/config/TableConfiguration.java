package tools.mygenerator.config;

import java.util.Set;

/** 
* 数据库表配置类
* @author 作者 : zyq
* 创建时间：2016年12月18日 下午10:32:33 
* @version 
*/
public class TableConfiguration {
	
	private Set<String> ignoredColumns;
	private String catalog;
    private String schema;
    private String tableName;
    
    
    
	public Set<String> getIgnoredColumns() {
		return ignoredColumns;
	}
	public void setIgnoredColumns(Set<String> ignoredColumns) {
		this.ignoredColumns = ignoredColumns;
	}
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
    
    
    
    

}
