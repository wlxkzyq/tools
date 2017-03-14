package tools.mygenerator.dictionary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tools.common.PathUtil;
import tools.mygenerator.api.IntrospectedColumn;
import tools.mygenerator.api.IntrospectedTable;
import tools.mygenerator.config.Context;
import tools.mygenerator.config.JDBCConnectionConfiguration;
import tools.mygenerator.config.TableChooseConfiguration;

/** 
* 获取数据库信息后做其它操作 
* @author 作者 : zyq
* 创建时间：2017年3月3日 下午6:58:02 
* @version 
*/
public class Start2 {
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
		chooseTables.add("t_wf_exportcfg");
		//chooseTables.add("t_wf_processinfo");
		tc.setChooseTables(chooseTables);
		//设置忽略表
//		Set<String> ignoreTables=new HashSet<String>();
//		ignoreTables.add("test");
//		tc.setIgnoredTables(ignoreTables);
		
		Context context=new Context();
		context.setJdbcConnectionConfiguration(c);
		context.setTableChooseConfiguration(tc);
		
		//警告信息存放
		List<String> warnings = null;
		List<IntrospectedTable> list=context.introspectTables(warnings);
		System.out.println(new Date().getTime()-d.getTime());
		
		IntrospectedTable it=list.get(0);
		
		FileOutputStream fos=new FileOutputStream("D:\\tt.txt");
		PrintWriter pw=new PrintWriter(fos);
		List<IntrospectedColumn> columns=it.getColumns();
		for (int i = 0; i < columns.size(); i++) {
			pw.println("t."+columns.get(i).getColumnName()+" as "+columns.get(i).getColumnName().toLowerCase()+",");
		}
		pw.flush();
		pw.close();
		
	}
}
