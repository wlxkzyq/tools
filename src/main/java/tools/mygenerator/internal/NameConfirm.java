package tools.mygenerator.internal;

import tools.mygenerator.api.IntrospectedColumn;
import tools.mygenerator.api.IntrospectedTable;
import tools.mygenerator.config.Context;
import tools.mygenerator.internal.util.JavaBeansUtil;

/** 
* 控制生成数据库表到对象名字的转换
* @author 作者 : zyq
* 创建时间：2017年3月15日 下午6:08:54 
* @version 
*/
public class NameConfirm {
	
	private boolean globalNameCamelCaseEnable;
	private Context context;
	public NameConfirm(Context context) {
		this.context=context;
		globalNameCamelCaseEnable=this.context.isGlobalNameCamelCaseEnable();
	}
	
	/**
	 * 数据库表生成实体类时获取实体类名称
	 * beanNameCamelCaseEnable 如果为'Y' ,使用驼峰式命名规则，且首字母大写
	 * 						   如果为其它，使用首字母大写的方式，其它不变
	 * @param context
	 * @param table
	 * @return
	 */
	public String getBeanName(IntrospectedTable table){
		if(globalNameCamelCaseEnable){
			return JavaBeansUtil.getCamelCaseString(table.getTableName(), true);
		}else{
			String tableName=table.getTableName();
			return Character.toUpperCase(tableName.charAt(0))+tableName.substring(1);
		}
	}
	
	/**
	 * 数据库表的列生成实体类时 对应的field 名字生成
	 * 如果beanFieldNameCamelCaseEnable 为{@code true} 采用首字母小写的驼峰式命名
	 * 如果为否，全部使用小写
	 * @param context
	 * @param column
	 * @return
	 */
	public String getBeanFieldName(IntrospectedColumn column){
		if(globalNameCamelCaseEnable){
			return JavaBeansUtil.getCamelCaseString(column.getColumnName(), false);
		}else{
			return column.getColumnName().toLowerCase();
		}
	}
	
	/**
	 * 根据数据库字段名获取实体类字段名
	 * 如果全局配置 beanFieldNameCamelCaseEnable 为{@code true} 采用首字母小写的驼峰式命名
	 * @param column
	 * @return
	 */
	public String getBeanFieldName(String column){
		if(globalNameCamelCaseEnable){
			return JavaBeansUtil.getCamelCaseString(column, false);
		}else{
			return column.toLowerCase();
		}
	}
}
