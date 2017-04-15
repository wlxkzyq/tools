package tools.mygenerator.config;

import java.util.HashMap;
import java.util.Map;

/** 
* 数据库列类型对照java成员变量类型映射类
* @author 作者 : zyq
* 创建时间：2017年3月7日 下午4:12:22 
* @version 
*/
public class ConstantConfig {
	
	public final static Map<String,String> columnMap=new HashMap<String,String>();
	static{
		columnMap.put("varchar", "java.lang.String");
		columnMap.put("date", "java.util.Date");
		columnMap.put("datetime", "java.util.Date");
		columnMap.put("time", "java.util.Date");
		columnMap.put("timestamp", "java.util.Date");
		columnMap.put("bigint", "java.lang.Long");
		columnMap.put("tinyint", "java.lang.Byte");
		columnMap.put("bit", "java.lang.Byte");
		columnMap.put("smallint", "java.lang.Short");
		columnMap.put("int", "java.lang.Integer");
		columnMap.put("float", "java.lang.Float");
		columnMap.put("double", "java.lang.Double");
		columnMap.put("char", "java.lang.String");
		columnMap.put("text", "java.lang.String");
		columnMap.put("varbinary", "byte[]");
	}
	

}
