package tools.mygenerator.api;

import java.util.List;
import java.util.Properties;

import tools.mygenerator.api.dom.java.TopLevelClass;
import tools.mygenerator.api.dom.xml.Document;
import tools.mygenerator.config.Context;

/** 
* 插件适配器
* 编写的插件强烈建议继承此类
* @author 作者 : zyq
* 创建时间：2017年3月10日 上午10:05:05 
* @version 
*/
public abstract class PluginAdapter implements Plugin {
	protected Context context;
	
	protected Properties properties;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	@Override
	public void initialized(IntrospectedTable introspectedTable) {
	}
	
	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
		return null;
	}
	
	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		return null;
	}
	
	@Override
	public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles() {
		return null;
	}
	
	@Override
	public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable) {
		return null;
	}
	
	
	
	
	public boolean modelClassGenerator(TopLevelClass topLevelClass,IntrospectedTable table){
    	return true;
    }
	
	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		return true;
	}
	
	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile generatedXmlFile, IntrospectedTable introspectedTable) {
		return true;
	}

}
