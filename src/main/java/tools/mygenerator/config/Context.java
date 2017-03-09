package tools.mygenerator.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.mybatis.generator.api.GeneratedJavaFile;

import tools.common.PathUtil;
import tools.mygenerator.api.CommentGenerator;
import tools.mygenerator.api.GeneratedXmlFile;
import tools.mygenerator.api.IntrospectedTable;
import tools.mygenerator.dictionary.GenerateDictionary;
import tools.mygenerator.internal.db.ConnectionFactory;
import tools.mygenerator.internal.db.DatabaseIntrospector;


/** 
* 数据库容器
* @author 作者 : zyq
* 创建时间：2016年12月13日 下午10:07:54 
* @version 
*/
public class Context {
	
	/**
	 * jdbc链接配置
	 */
	private JDBCConnectionConfiguration jdbcConnectionConfiguration;
	
	/**
	 * 表选择配置
	 */
	private TableChooseConfiguration tableChooseConfiguration;
	
	/**
	 * 注释生成类
	 */
	private CommentGenerator commentGenerator;
	
	private JavaModelGeneratorConfiguration javaModelGeneratorConfiguration;
	
	private final Logger logger=Logger.getLogger(Context.class);
	
	
    public void setJdbcConnectionConfiguration(JDBCConnectionConfiguration jdbcConnectionConfiguration) {
		this.jdbcConnectionConfiguration = jdbcConnectionConfiguration;
	}

	public void setTableChooseConfiguration(TableChooseConfiguration tableChooseConfiguration) {
		this.tableChooseConfiguration = tableChooseConfiguration;
	}
	public CommentGenerator getCommentGenerator() {
		return commentGenerator;
	}

	public void setCommentGenerator(CommentGenerator commentGenerator) {
		this.commentGenerator = commentGenerator;
	}
	public JavaModelGeneratorConfiguration getJavaModelGeneratorConfiguration() {
		return javaModelGeneratorConfiguration;
	}
	public void setJavaModelGeneratorConfiguration(JavaModelGeneratorConfiguration javaModelGeneratorConfiguration) {
		this.javaModelGeneratorConfiguration = javaModelGeneratorConfiguration;
	}

	
	
	/**
	 * 将数据库信息封装为实体类
	 * @return
	 * @throws SQLException
	 */
    public List<IntrospectedTable> introspectTables(List<String> warnings){
    	Connection connection = null;
    	try {
			connection = getConnection();
			DatabaseMetaData meta=connection.getMetaData();
			System.out.println(meta.getDatabaseProductName());
			System.out.println("===================");
			//databaseInfo(meta);
	    	DatabaseIntrospector di=new DatabaseIntrospector();
	    	di.setDatabaseMetaData(meta);
	    	List<IntrospectedTable> list=di.introspectTables(tableChooseConfiguration, warnings,null);
	    	return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	return null;
    }
    
    /**
     * 获取数据库连接
     * @return
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        Connection connection = ConnectionFactory.getInstance().getConnection(
                jdbcConnectionConfiguration);
        return connection;
    }
    
    /**
     * 生成file类，添加到集合
     * @param generatedJavaFiles	存放java文件的集合
     * @param generatedXmlFiles		存放xml文件的集合
     * @param warnings				存放警告信息
     */
    public void generateFiles(List<GeneratedJavaFile> generatedJavaFiles,
    		List<GeneratedXmlFile> generatedXmlFiles,List<String> warnings){
    	
    }
    
    
    
    
    
    
    public static void main(String[] args) throws SQLException {
    	Date d=new Date();
    	
    	JDBCConnectionConfiguration c=new JDBCConnectionConfiguration();
		c.setConnectionURL("jdbc:mysql://120.25.220.63:3306/fsstp_db");
		c.setDriverClass("com.mysql.jdbc.Driver");
		c.setPassword("life12345");
		c.setUserId("fsstp_admin");
//		c.setConnectionURL("jdbc:mysql://localhost:3306/fhadmin");
//		c.setDriverClass("com.mysql.jdbc.Driver");
//		c.setPassword("admin");
//		c.setUserId("root");
		Context context=new Context();
		context.setJdbcConnectionConfiguration(c);
		List<String> warnings = null;
		context.introspectTables(warnings);
		System.out.println(new Date().getTime()-d.getTime());
	}
    public void databaseInfo(DatabaseMetaData databaseMeta) throws SQLException, IOException{
    	//获取该连接所有的数据库
    	//ResultSet resultSet=databaseMeta.getCatalogs();
    	//没搞明白？？？？
    	//ResultSet resultSet=databaseMeta.getColumnPrivileges("ijob", "%", "type", "type_name");
    	//获取catalog下所有的表，表名 % 匹配所有表
    	//ResultSet resultSet=databaseMeta.getTables("ijob", null, "%", new String[]{"table"});
    	//表类型：table; view ; local temporary
    	//ResultSet resultSet=databaseMeta.getTableTypes();
    	
    	//列信息
    	//catalog   schema   表名              列名          数据类型       类型名字       列长度    buffer_length   DECIMAL_DIGITS   NUM_PREC_RADIX   是否可空       备注          默认                               是否能存null                                是否递增
    	//ijob,     null,    url_info,  url_id,  4,        INT,       10,     65535,          0,              10,              0,         网址主键,null,0,0,null,1,NO,            null,null,null,null,        YES,
    	ResultSet resultSet=databaseMeta.getColumns("ijob", null, "url_info", "%");
    	
    	//主键表catalog  schema   表名    字段            外键表catalog   schema  外键表名       外键字段                  外键名
    	//ij	ob,          null,   type, type_id,  ijob,          null,   url_info,  type_id,  1,3,3,FK_URL_TYPE,null,7,
    	//ResultSet resultSet=databaseMeta.getImportedKeys("ijob", null, "url_info");
    	
    	//跟上面的类似
    	//ijob,null,type,type_id,ijob,null,url_info,type_id,1,3,3,FK_URL_TYPE,null,7,
    	//ResultSet resultSet=databaseMeta.getExportedKeys("ijob", null, "type");
    	
    	//主键信息
    	//catalog  schema  表名       字段           KEY_SEQ    主键名字
    	//ijob,    null,   type,  type_id, 1,         PRIMARY,
    	//ResultSet resultSet=databaseMeta.getPrimaryKeys("ijob", null, "type");
    	
    	
    	
    	
    	ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
    	int size=resultSetMetaData.getColumnCount();
    	while (resultSet.next()) {
    		for (int i = 0; i < size; i++) {
    			System.out.print(resultSet.getString(1+i)+",");
			}
			System.out.println();
			
		}
    	
    	boolean d=databaseMeta.supportsGetGeneratedKeys();
    	
    	System.out.println(d);
    	
    	
    	List<String> warnings=new ArrayList<String>();
    	TableChooseConfiguration tc=new TableChooseConfiguration();
    	tc.setCatalog("fsstp_db");
    	Set<String> chooseTable=new HashSet<String>();
    	//chooseTable.add("url_info");
    	tc.setChooseTables(chooseTable );
    	tc.setNameLike("%");
    	DatabaseIntrospector di=new DatabaseIntrospector();
    	di.setDatabaseMetaData(databaseMeta);
    	
    	List<IntrospectedTable> list=di.introspectTables(tc, warnings,null);
    	//list=list.subList(0, 10);
    	FileInputStream fis=new FileInputStream(PathUtil.getClassPath()+"generator"+File.separator+"dictionary_temp.xls");
    	GenerateDictionary gd=new GenerateDictionary(fis);
    	gd.generateDictionary("d://jjj2.xls", list);
    	System.out.println(warnings);
    	
    }

}
