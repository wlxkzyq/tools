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
	
	private boolean beanNameCamelCaseEnable;
	private boolean beanFieldNameCamelCaseEnable;
	private Context context;
	public NameConfirm(Context context) {
		this.context=context;
		beanNameCamelCaseEnable=getProperty("beanNameCamelCaseEnable");
		beanFieldNameCamelCaseEnable=getProperty("beanFieldNameCamelCaseEnable");
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
		if(beanNameCamelCaseEnable){
			return JavaBeansUtil.getCamelCaseString(table.getTableName(), true);
		}else{
			String tableName=table.getTableName();
			return Character.toUpperCase(tableName.charAt(0))+tableName.substring(1);
		}
	}
	
	/**
	 * 数据库表的列生成实体类时 对应的field 名字生成
	 * 如果beanFieldNameCamelCaseEnable 为'Y'||null 采用首字母小写的驼峰式命名
	 * 如果为否，全部使用小写
	 * @param context
	 * @param column
	 * @return
	 */
	public String getBeanFieldName(IntrospectedColumn column){
		if(beanFieldNameCamelCaseEnable){
			return JavaBeansUtil.getCamelCaseString(column.getColumnName(), false);
		}else{
			return column.getColumnName().toLowerCase();
		}
	}
	/**
	 * 获取配置信息（只获取值为是否类型的）
	 * @param key
	 * @return
	 */
	private boolean getProperty(String key){
		String value=context.getJavaModelGeneratorConfiguration().getProperty(key);
		if(value==null||"Y".equals(value)){
			return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args) {
		String tableName="Dsfa";
		String a= Character.toUpperCase(tableName.charAt(0))+tableName.substring(1);
		System.out.println(a);
	}
}
