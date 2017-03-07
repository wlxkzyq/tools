package tools.mygenerator.codegen;

import java.util.List;

import tools.mygenerator.api.IntrospectedTable;
import tools.mygenerator.config.Context;

/** 
* 代码生成器抽象类
* @author 作者 : zyq
* 创建时间：2017年3月7日 上午10:39:00 
* @version 
*/
public class AbstractGenerator {
	/**
	 * 配置信息
	 */
	protected Context context;
	/**
	 * 数据库表信息
	 */
	protected IntrospectedTable introspectedTable;
	/**
	 * 警告信息
	 */
	protected List<String> warnings;
	
	
	public AbstractGenerator() {
        super();
    }


	public Context getContext() {
		return context;
	}


	public void setContext(Context context) {
		this.context = context;
	}


	public IntrospectedTable getIntrospectedTable() {
		return introspectedTable;
	}


	public void setIntrospectedTable(IntrospectedTable introspectedTable) {
		this.introspectedTable = introspectedTable;
	}


	public List<String> getWarnings() {
		return warnings;
	}


	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}
	
	

}
