package tools.mygenerator.plugins;

import java.util.List;


import tools.mygenerator.api.IntrospectedTable;
import tools.mygenerator.api.PluginAdapter;
import tools.mygenerator.api.dom.java.Field;
import tools.mygenerator.api.dom.java.FullyQualifiedJavaType;
import tools.mygenerator.api.dom.java.JavaVisibility;
import tools.mygenerator.api.dom.java.Method;
import tools.mygenerator.api.dom.java.TopLevelClass;

/** 
* toString 方法 添加插件
* @author 作者 : zyq
* 创建时间：2017年3月10日 上午10:35:14 
* @version 
*/
public class ToStringPlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}
	
	@Override
	public boolean modelClassGenerator(TopLevelClass topLevelClass, IntrospectedTable table) {
		generateToString(table,topLevelClass);
		return super.modelClassGenerator(topLevelClass, table);
	}
	
	
	  private void generateToString(IntrospectedTable introspectedTable,
	            TopLevelClass topLevelClass) {
	        Method method = new Method();
	        method.setVisibility(JavaVisibility.PUBLIC);
	        method.setReturnType(FullyQualifiedJavaType.getStringInstance());
	        method.setName("toString"); //$NON-NLS-1$
	        //添加override 注解
	        method.addAnnotation("@Override"); //$NON-NLS-1$

	        context.getCommentGenerator().addGeneralMethodComment(method,
	                introspectedTable);

	        method.addBodyLine("StringBuilder sb = new StringBuilder();"); //$NON-NLS-1$
	        method.addBodyLine("sb.append(getClass().getSimpleName());"); //$NON-NLS-1$
	        method.addBodyLine("sb.append(\" [\");"); //$NON-NLS-1$
	        method.addBodyLine("sb.append(\"Hash = \").append(hashCode());"); //$NON-NLS-1$
	        StringBuilder sb = new StringBuilder();
	        for (Field field : topLevelClass.getFields()) {
	            String property = field.getName();
	            sb.setLength(0);
	            sb.append("sb.append(\"").append(", ").append(property) //$NON-NLS-1$ //$NON-NLS-2$
	                    .append("=\")").append(".append(").append(property) //$NON-NLS-1$ //$NON-NLS-2$
	                    .append(");"); //$NON-NLS-1$
	            method.addBodyLine(sb.toString());
	        }

	        method.addBodyLine("sb.append(\"]\");"); //$NON-NLS-1$
	        method.addBodyLine("return sb.toString();"); //$NON-NLS-1$

	        topLevelClass.addMethod(method);
	    }
	

}
