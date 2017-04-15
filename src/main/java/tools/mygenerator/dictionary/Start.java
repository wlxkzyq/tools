package tools.mygenerator.dictionary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import tools.common.PathUtil;
import tools.mygenerator.api.IntrospectedTable;
import tools.mygenerator.config.Context;
import tools.mygenerator.config.JDBCConnectionConfiguration;
import tools.mygenerator.config.TableChooseConfiguration;

/** 
* 生成数据字典开始类
* @author 作者 : zyq
* 创建时间：2017年2月28日 下午4:43:49 
* @version 
*/
public class Start {
	
	
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
		chooseTables.add("t_sr_problem_process");
//		chooseTables.add("user");
		tc.setChooseTables(chooseTables);
		//设置忽略表
		Set<String> ignoreTables=new HashSet<String>();
//		ignoreTables.add("test");
		tc.setIgnoredTables(ignoreTables);
		
		Context context=new Context();
		context.setJdbcConnectionConfiguration(c);
		context.setTableChooseConfiguration(tc);
		
		//警告信息存放
		List<String> warnings = null;
		List<IntrospectedTable> list=context.introspectTables(warnings);
		System.out.println(new Date().getTime()-d.getTime());
		
		FileInputStream fis=new FileInputStream(PathUtil.getClassPath()+"generator"+File.separator+"dictionary_temp.xls");
    	GenerateDictionary gd=new GenerateDictionary(fis);
    	gd.generateDictionary("d://jjj2.xls", list);
    	
	}

}
