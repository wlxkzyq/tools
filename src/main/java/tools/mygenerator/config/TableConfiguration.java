package tools.mygenerator.config;

import java.util.Set;

/** 
* 数据库表配置类
* @author 作者 : zyq
* 创建时间：2016年12月18日 下午10:32:33 
* @version 
*/
public class TableConfiguration {
	
	/**
	 * 被忽略的列，输入值时必须使用小写
	 */
	private Set<String> ignoredColumns;
	private String catalog;
    private String schema;
    private String tableName;
    
    /**
     * 生成实体类配置
     */
    private JavaModelGeneratorConfiguration javaModelGeneratorConfiguration;
    
    
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
	public JavaModelGeneratorConfiguration getJavaModelGeneratorConfiguration() {
		return javaModelGeneratorConfiguration;
	}
	public void setJavaModelGeneratorConfiguration(JavaModelGeneratorConfiguration javaModelGeneratorConfiguration) {
		this.javaModelGeneratorConfiguration = javaModelGeneratorConfiguration;
	}
    
    
    
    

}
