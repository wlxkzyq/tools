package tools.mygenerator.config;

import java.util.Set;

/** 
* 要操作的表配置类
* @author 作者 : zyq
* 创建时间：2016年12月18日 下午10:55:30 
* @version 
*/
public class TableChooseConfiguration {
	
	private String catalog;
    private String schema;
    
	/**
	 * 被选择的表(如果此属性和nameLike属性都存在，以该属性为准！！！)
	 */
	private Set<String> chooseTables;
	/**
	 * 被忽略的表
	 */
	private Set<String> ignoredTables;
	/**
	 * 按照数据库模糊查询的方法进行筛选表
	 * %         : 包含零个或更多字符的任意字符串。
	 * _（下划线) : 任何单个字符。
	 * [ ]       : 指定范围 ([a-f]) 或集合 ([abcdef]) 中的任何单个字符。
	 * [^]		 : 不属于指定范围 ([a-f]) 或集合 ([abcdef]) 的任何单个字符。
	 */
	private String nameLike;
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
	public Set<String> getChooseTables() {
		return chooseTables;
	}
	public void setChooseTables(Set<String> chooseTables) {
		this.chooseTables = chooseTables;
	}
	public Set<String> getIgnoredTables() {
		return ignoredTables;
	}
	public void setIgnoredTables(Set<String> ignoredTables) {
		this.ignoredTables = ignoredTables;
	}
	public String getNameLike() {
		return nameLike;
	}
	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}
	
	
	

}
