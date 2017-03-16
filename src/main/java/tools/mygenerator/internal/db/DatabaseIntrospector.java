package tools.mygenerator.internal.db;

import static tools.mygenerator.internal.util.StringUtility.stringHasValue;
import static tools.mygenerator.internal.util.messages.Messages.getString;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import tools.mygenerator.api.IntrospectedColumn;
import tools.mygenerator.api.IntrospectedForeignKey;
import tools.mygenerator.api.IntrospectedPrimaryKey;
import tools.mygenerator.api.IntrospectedTable;
import tools.mygenerator.config.TableChooseConfiguration;
import tools.mygenerator.config.TableConfiguration;

/** 
* 数据库信息封装类
* @author 作者 : zyq
* 创建时间：2016年12月17日 下午5:38:12 
* @version 
*/
public class DatabaseIntrospector {
	private DatabaseMetaData databaseMetaData;
	private Connection conn;
    private List<String> warnings;
    private final Logger logger=Logger.getLogger(DatabaseIntrospector.class);
    
    /**
     * 讲数据库信息封装成实体类
     * @param tc	表选择配置
     * @param warnings	警告信息存储
     * @param map	表配置map（key：表名，value：表配置）
     * @return
     * @throws SQLException
     */
    public List<IntrospectedTable> introspectTables(TableChooseConfiguration tc,List<String> warnings,Map<String,TableConfiguration> map) throws SQLException{
    	List<IntrospectedTable> introspectedTables=new ArrayList<IntrospectedTable>();
    	Set<String> chooseTables=tc.getChooseTables();
    	IntrospectedTable table=null;
    	String remark="";
    	
    	//当前操作的第几个表
    	int current=0;
    	//如果被选择的表不为空0000
    	if(chooseTables!=null&&chooseTables.size()>0){
    		Iterator<String> iterator=chooseTables.iterator();
    		String tableName="";
    		while (iterator.hasNext()) {
    			tableName=iterator.next();
    			current++;
    			logger.debug("正在读取第   "+current+"   个表，表名："+tableName);
    			if(tc.getIgnoredTables()!=null&&tc.getIgnoredTables().contains(tableName)){
    				warnings.add(tableName+"===被忽略");
    				continue;
    				}
    			ResultSet resultSet=databaseMetaData.getTables(tc.getCatalog(), tc.getSchema(), tableName, new String[]{"table"});
    			if(!resultSet.next()){
    				warnings.add(tableName+getString("Warning.0"));
				}else{
					table=new IntrospectedTable();
					table.init(tc.getCatalog(), tc.getSchema(), tableName);
					//赋值注释
					table.setRemarks(resultSet.getString("REMARKS"));
					//赋值外键
					fillForeignKey(table);
					//赋值主键
					fillPrimaryKey(table);
					fillColumns(table,map!=null?map.get(tableName):null);
					//如果注释没有查询到
					if(!stringHasValue(table.getRemarks())){
						table.setRemarks(getTableRemark(tableName));
					}
				}
    			introspectedTables.add(table);
			}
    	}else{
    		//没有指定操作哪些表，用模糊查询匹配表
    		String tableLike=tc.getNameLike();
    		if(tableLike==null||tableLike.trim().length()==0){tableLike="%";}
    		ResultSet resultSet=databaseMetaData.getTables(tc.getCatalog(), tc.getSchema(), tableLike, new String[]{"table"});
    		String tableName="";
    		while (resultSet.next()) {
    			tableName=resultSet.getString("TABLE_NAME");
    			current++;
    			logger.debug("正在读取第   "+current+"   个表，表名："+tableName);
    			if(tc.getIgnoredTables()!=null&&tc.getIgnoredTables().contains(tableName)){
    				warnings.add(tableName+"===被忽略");
    				continue;
    				}
    			table=new IntrospectedTable();
				table.init(resultSet.getString("TABLE_CAT"), resultSet.getString("TABLE_SCHEM"),tableName );
				//赋值注释
				table.setRemarks(resultSet.getString("REMARKS"));
				//赋值外键
				fillForeignKey(table);
				//赋值主键
				fillPrimaryKey(table);
				fillColumns(table,map!=null?map.get(tableName):null);
				//如果注释没有查询到
				if(!stringHasValue(table.getRemarks())){
					table.setRemarks(getTableRemark(tableName));
				}
				introspectedTables.add(table);
    		}
    		
    		
    	}
    	
    	return introspectedTables;
    }
    
    
    //处理databasemeta获取不到表注释的情况
    public String getTableRemark(String tableName) throws SQLException{
    	if(conn==null){
    		conn=databaseMetaData.getConnection();
    	}
    	String sql="show create table "+tableName;
    	PreparedStatement ps=conn.prepareStatement(sql);
    	ResultSet rs=ps.executeQuery();
    	String remark="";
    	while (rs.next()) {
    		String cc=rs.getString(2);
    		int index=cc.lastIndexOf('=');
    		if(cc.substring(index-7).toLowerCase().startsWith("comment")){
    			remark=cc.substring(index+2,cc.length()-1);
    		}
		}
    	
    	return remark;
    }
    //获取表的外键信息
    public void fillForeignKey(IntrospectedTable t) throws SQLException{
    	ResultSet resultSet=databaseMetaData.getImportedKeys(t.getTableCatalog(), t.getTableSchema(), t.getTableName());
    	Set<IntrospectedForeignKey> fkSet=new HashSet<IntrospectedForeignKey>();
    	IntrospectedForeignKey fk=null;
    	while (resultSet.next()) {
    		fk=new IntrospectedForeignKey();
    		fk.setDeferrAbility(resultSet.getShort("DEFERRABILITY"));
    		fk.setDeleteRule(resultSet.getShort("DELETE_RULE"));
    		fk.setFkColumnName(resultSet.getString("FKCOLUMN_NAME"));
    		fk.setFkName(resultSet.getString("FK_NAME"));
    		fk.setFkTableCatalog(resultSet.getString("FKTABLE_CAT"));
    		fk.setFkTableName(resultSet.getString("FKTABLE_NAME"));
    		fk.setFkTableSchema(resultSet.getString("FKTABLE_SCHEM"));
    		fk.setKeySeq(resultSet.getShort("KEY_SEQ"));
    		fk.setPkColumnName(resultSet.getString("PKCOLUMN_NAME"));
    		fk.setPkTableCatalog(resultSet.getString("PKTABLE_CAT"));
    		fk.setPkTableName(resultSet.getString("PKTABLE_NAME"));
    		fk.setPkTableSchema(resultSet.getString("PKTABLE_SCHEM"));
    		fk.setUpdateRule(resultSet.getShort("UPDATE_RULE"));
    		
    		fkSet.add(fk);
		}
    	t.setForeignKeys(fkSet);
    }
    
  //获取表的主键信息
    public void fillPrimaryKey(IntrospectedTable t) throws SQLException{
    	ResultSet resultSet=databaseMetaData.getPrimaryKeys(t.getTableCatalog(), t.getTableSchema(), t.getTableName());
    	Set<IntrospectedPrimaryKey> pkSet=new HashSet<IntrospectedPrimaryKey>();
    	IntrospectedPrimaryKey pk=null;
    	while (resultSet.next()) {
    		pk=new IntrospectedPrimaryKey();
    		pk.setColumnName(resultSet.getString("COLUMN_NAME"));
    		pk.setKeySeq(resultSet.getShort("KEY_SEQ"));
    		pk.setPrimaryKeyName(resultSet.getString("PK_NAME"));
    		pk.setTableCatalog(resultSet.getString("TABLE_CAT"));
    		pk.setTableName(resultSet.getString("TABLE_NAME"));
    		pk.setTableSchema(resultSet.getString("TABLE_SCHEM"));
    		pkSet.add(pk);
		}
    	t.setPrimaryKeys(pkSet);
    }
    
    //获取表的列信息
    public void fillColumns(IntrospectedTable t,TableConfiguration tc) throws SQLException{
    	ResultSet resultSet=databaseMetaData.getColumns(t.getTableCatalog(), t.getTableSchema(), t.getTableName(),"%");
    	List<IntrospectedColumn> columns=new ArrayList<IntrospectedColumn>();
    	IntrospectedColumn column=null;
    	Set<String> ignoreColumns=null;
    	if(tc!=null){
    		ignoreColumns=tc.getIgnoredColumns();
    	}
    	
    	while (resultSet.next()) {
    		//如果该列被忽略
    		if(ignoreColumns!=null&&ignoreColumns.contains(resultSet.getString("COLUMN_NAME")))continue;
    		
    		column=new IntrospectedColumn();
    		column.setColumnName(resultSet.getString("COLUMN_NAME"));
    		column.setColumnSize(resultSet.getInt("COLUMN_SIZE"));
    		column.setDataType(resultSet.getInt("DATA_TYPE"));
    		column.setDecimalDigits(resultSet.getInt("DECIMAL_DIGITS"));
    		column.setIsAutoIncrement(resultSet.getString("IS_AUTOINCREMENT"));
    		column.setNullable(resultSet.getInt("NULLABLE"));
    		column.setRemarks(resultSet.getString("REMARKS"));
    		column.setTableCatalog(resultSet.getString("TABLE_CAT"));
    		column.setTableName(resultSet.getString("TABLE_NAME"));
    		column.setTableSchema(resultSet.getString("TABLE_SCHEM"));
    		column.setTypeName(resultSet.getString("TYPE_NAME"));
    		column.setDefaultValue(resultSet.getString("COLUMN_DEF"));
    		columns.add(column);
		}
    	t.setColumns(columns);
    }
    
    //以下是get set方法
	public DatabaseMetaData getDatabaseMetaData() {
		return databaseMetaData;
	}

	public void setDatabaseMetaData(DatabaseMetaData databaseMetaData) {
		this.databaseMetaData = databaseMetaData;
	}
    
    public static void main(String[] args) {
		Set<String> set=new HashSet<String>();
		set.add("hello");
		System.out.println("hello"==new String("hello"));
		boolean d=set.contains(new String("hello"));
		System.out.println(d);
	}
    
    
    
}
