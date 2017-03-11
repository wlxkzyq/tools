package tools.mygenerator.dictionary;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tools.mygenerator.api.GeneratedJavaFile;
import tools.mygenerator.api.IntrospectedTable;
import tools.mygenerator.api.dom.DefaultJavaFormatter;
import tools.mygenerator.api.dom.java.Field;
import tools.mygenerator.api.dom.java.Method;
import tools.mygenerator.codegen.AbstractJavaGenerator;
import tools.mygenerator.codegen.JavaEntityGenerator;
import tools.mygenerator.config.Context;
import tools.mygenerator.config.JDBCConnectionConfiguration;
import tools.mygenerator.config.JavaModelGeneratorConfiguration;
import tools.mygenerator.config.TableChooseConfiguration;
import tools.mygenerator.internal.DefaultCommentGenerator;

/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月7日 上午11:12:11 
* @version 
*/
public class Start3 {
	public static void main(String[] args) throws Exception {
		Date d=new Date();
		
		//设置jdbc链接配置
    	JDBCConnectionConfiguration c=new JDBCConnectionConfiguration();
//		c.setConnectionURL("jdbc:mysql://120.25.220.63:3306/fsstp_db");
//		c.setDriverClass("com.mysql.jdbc.Driver");
//		c.setPassword("life12345");
//		c.setUserId("fsstp_admin");
		c.setConnectionURL("jdbc:mysql://localhost:3306/ngwf_he_dev");
		c.setDriverClass("com.mysql.jdbc.Driver");
		c.setUserId("root");
		c.setPassword("admin");
		
		//设置选择表
		TableChooseConfiguration tc=new TableChooseConfiguration();
		tc.setCatalog("ngwf_he_dev");
		tc.setSchema("");
		tc.setNameLike("%");
		
		//设置指定表
		Set<String> chooseTables=new HashSet<String>();
		//chooseTables.add("t_wf_workitem");
		chooseTables.add("t_wf_processinfo");
		tc.setChooseTables(chooseTables);
		//设置忽略表
//		Set<String> ignoreTables=new HashSet<String>();
//		ignoreTables.add("test");
//		tc.setIgnoredTables(ignoreTables);
		
		Context context=new Context();
		context.setJdbcConnectionConfiguration(c);
		context.setTableChooseConfiguration(tc);
		context.setCommentGenerator(new DefaultCommentGenerator());
		
		
		//以下设置生成代码相关配置
		//======================
		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration=new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.addProperty("trimStrings", "true");
		javaModelGeneratorConfiguration.setTargetPackage("tools.test");
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
		
		//警告信息存放
		List<String> warnings = new ArrayList<String>();
		List<IntrospectedTable> list=context.introspectTables(warnings);
		System.out.println(new Date().getTime()-d.getTime());
		
		IntrospectedTable it=list.get(0);
		
		/*AbstractJavaGenerator ajb=new AbstractJavaGenerator();
		ajb.setContext(context);
		ajb.setIntrospectedTable(it);
		ajb.setWarnings(warnings);
		Field field=ajb.getJavaBeansField(it.getColumns().get(1));
		Method set=ajb.getJavaBeansSetter(it.getColumns().get(1));
		Method get=ajb.getJavaBeansGetter(it.getColumns().get(1));
		System.out.println(field.getFormattedContent(0));
		System.out.println(set.getFormattedContent(0, false));
		System.out.println(get.getFormattedContent(0, false));
		System.out.println(warnings);*/
		
//		JavaEntityGenerator jg=new JavaEntityGenerator();
//		jg.setContext(context);
//		jg.setIntrospectedTable(it);
//		jg.setWarnings(warnings);
//		GeneratedJavaFile generatedJavaFile=
//				new GeneratedJavaFile(jg.getCompilationUnits().get(0), "tools", new DefaultJavaFormatter());
//		System.out.println(generatedJavaFile.getFormattedContent());
//		
//		FileOutputStream fos=new FileOutputStream("D://111.java");
//		fos.write(generatedJavaFile.getFormattedContent().getBytes());
//		fos.close();
	
		
	}
}
