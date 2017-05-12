package tools.mygenerator.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

import tools.mygenerator.config.Context;
import tools.mygenerator.config.GeneratorRegistry;
import tools.mygenerator.config.JDBCConnectionConfiguration;
import tools.mygenerator.config.JavaModelGeneratorConfiguration;
import tools.mygenerator.config.PluginConfiguration;
import tools.mygenerator.config.PropertyRegistry;
import tools.mygenerator.config.SqlMapGeneratorConfiguration;
import tools.mygenerator.config.TableChooseConfiguration;

/** 
* 生成开始方法
* @author 作者 : zyq
* 创建时间：2017年3月11日 上午9:33:59 
* @version 
*/
public class GeneratorStart {
	public static void main(String[] args) throws Exception {
		Context context=new Context();
		List<String> warnings=new ArrayList<String>();		
		Date d=new Date();
		
		
		/**
		 * context 配置
		 */
		//设置jdbc链接配置
    	JDBCConnectionConfiguration c=new JDBCConnectionConfiguration();
//		c.setConnectionURL("jdbc:mysql://120.25.220.63:3306/fsstp_db");
//		c.setDriverClass("com.mysql.jdbc.Driver");
//		c.setPassword("life12345");
//		c.setUserId("fsstp_admin");
		c.setConnectionURL("jdbc:mysql://localhost:3306/yzt_db");
		c.setDriverClass("com.mysql.jdbc.Driver");
		c.setUserId("root");
		c.setPassword("admin");
		
		//设置选择表
		TableChooseConfiguration tc=new TableChooseConfiguration();
		tc.setCatalog("yzt_db");
		tc.setSchema("");
		tc.setNameLike("%");
		
		//设置指定表
		Set<String> chooseTables=new HashSet<String>();
		//chooseTables.add("t_wf_workitem");
		//chooseTables.add("t_wf_processinfo");
		chooseTables.add("public_config");
		tc.setChooseTables(chooseTables);
		//设置忽略表
//		Set<String> ignoreTables=new HashSet<String>();
//		ignoreTables.add("test");
//		tc.setIgnoredTables(ignoreTables);
		
		context.setJdbcConnectionConfiguration(c);
		context.setTableChooseConfiguration(tc);
//		context.setCommentGenerator(new DefaultCommentGenerator());
		
		
		//以下设置生成代码相关配置
		//======================
		//======配置java实体类生成规则
		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration=new JavaModelGeneratorConfiguration();
		//设置是否set方法参数trim
		javaModelGeneratorConfiguration.setClassNamePrefix("");
		javaModelGeneratorConfiguration.setClassNameSuffix("Entiy");
		javaModelGeneratorConfiguration.addProperty(PropertyRegistry.MODEL_GENERATOR_TRIM_STRINGS, "true");
		javaModelGeneratorConfiguration.setTargetPackage("tools.test.entity");
		javaModelGeneratorConfiguration.setTargetProject("D:/hhh");
		//设置继承类
		javaModelGeneratorConfiguration.addProperty(PropertyRegistry.ANY_ROOT_CLASS, "PageData");
		
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
		
		//======配置sqlMapper生成规则
		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration=new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetPackage("tools.test.mapper");
		sqlMapGeneratorConfiguration.setTargetProject("D:/hhh");
		
		//设置生成文件前缀、后缀
		sqlMapGeneratorConfiguration.setFileNamePrefix("");
		sqlMapGeneratorConfiguration.setFileNameSuffi("Mapper");
		
		//设置通过条件查询<select> 的id前缀、后缀
		sqlMapGeneratorConfiguration.addProperty(PropertyRegistry.MAPPER_SELECTBYCONDITION_ID_PREFIX, "select");
		sqlMapGeneratorConfiguration.addProperty(PropertyRegistry.MAPPER_SELECTBYCONDITION_ID_SUFFIX, "ListPage");
		
		//设置通过主键查询<select> 的id前缀、后缀
		sqlMapGeneratorConfiguration.addProperty(PropertyRegistry.MAPPER_SELECTBYPRIMARYKEY_ID_PREFIX, "select");
		sqlMapGeneratorConfiguration.addProperty(PropertyRegistry.MAPPER_SELECTBYPRIMARYKEY_ID_SUFFIX, "ByPrimaryKey");
		
		//配置所有表生成sqlMapper时使用的别名
		sqlMapGeneratorConfiguration.addProperty("tableAlias", "");
		//配置是否生成tableName sql("Y"/"N")
		sqlMapGeneratorConfiguration.addProperty("generateTableNameEnable", "Y");
		//配置是否生成tableColumns sql("Y"/"N")
		sqlMapGeneratorConfiguration.addProperty("generateColumnsEnable", "Y");
		//配置是否生成通过条件查询<select>("Y"/"N")
		sqlMapGeneratorConfiguration.addProperty("generateSelectByConditionEnable", "Y");
		//配置是否生成通过主键查询<select>("Y"/"N")
		sqlMapGeneratorConfiguration.addProperty("generateSelectByPrimaryKeyEnable", "Y");
		//配置是否生成普通插入 sql("Y"/"N")
		sqlMapGeneratorConfiguration.addProperty("generateInsertEnable", "Y");
		//配置Mapper文件 <select>节点参数是否使用Map类的形式 ("Y"/"N")
		sqlMapGeneratorConfiguration.addProperty("mapParamEnable", "N");
		//配置Mapper文件 <where>节点条件形式
		sqlMapGeneratorConfiguration.addProperty("mapperWhereCondition", PropertyRegistry.MAPPER_WHERE_CONDITION_STATIC);
		//设置静态where条件
		String json="{\"allCriteria\":[{\"betweenValue\":false,\"condition\":\"id is not null\",\"listValue\":false,\"noValue\":true,\"property\":\"id\",\"simpleCondition\":\" is not null\",\"singleValue\":false},{\"betweenValue\":true,\"condition\":\"id between\",\"listValue\":false,\"noValue\":false,\"property\":\"id\",\"secondValue\":2,\"simpleCondition\":\" between\",\"singleValue\":false,\"value\":1},{\"betweenValue\":false,\"condition\":\"id in\",\"listValue\":true,\"noValue\":false,\"property\":\"id\",\"simpleCondition\":\" in\",\"singleValue\":false,\"value\":[]},{\"betweenValue\":false,\"condition\":\"id =\",\"listValue\":false,\"noValue\":false,\"property\":\"id\",\"simpleCondition\":\" =\",\"singleValue\":true,\"value\":{}}],\"criteria\":[{\"betweenValue\":false,\"condition\":\"id is not null\",\"listValue\":false,\"noValue\":true,\"property\":\"id\",\"simpleCondition\":\" is not null\",\"singleValue\":false},{\"betweenValue\":true,\"condition\":\"id between\",\"listValue\":false,\"noValue\":false,\"property\":\"id\",\"secondValue\":2,\"simpleCondition\":\" between\",\"singleValue\":false,\"value\":1},{\"betweenValue\":false,\"condition\":\"id in\",\"listValue\":true,\"noValue\":false,\"property\":\"id\",\"simpleCondition\":\" in\",\"singleValue\":false,\"value\":[]},{\"betweenValue\":false,\"condition\":\"id =\",\"listValue\":false,\"noValue\":false,\"property\":\"id\",\"simpleCondition\":\" =\",\"singleValue\":true,\"value\":{}}],\"valid\":true}";
		sqlMapGeneratorConfiguration.setCondition(JSONObject.parseObject(json));
		context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
		
		
		//======配置使用哪些生成器
		List<String> generators=new ArrayList<String>();
		generators.add(GeneratorRegistry.javaEntity);
		generators.add(GeneratorRegistry.mybatis3Mapper);
		context.setGenerators(generators);
		
		//======配置使用的插件
		List<PluginConfiguration> pluginConfigurations=new ArrayList<PluginConfiguration>();
		//添加toStringPlugin插件
		PluginConfiguration toStringPlugin=new PluginConfiguration();
		toStringPlugin.setConfigurationType("tools.mygenerator.plugins.ToStringPlugin");
		pluginConfigurations.add(toStringPlugin);
		context.setPluginConfigurations(pluginConfigurations);
		
		//======配置生成的文件是否覆盖
		context.setOverwriteEnabled(true);
		//配置全局是否使用驼峰命名
		context.setGlobalNameCamelCaseEnable(false);
		
		
		MyGenerator my=new MyGenerator(context, warnings);
		my.generate();
		for (String warning : warnings) {
			System.out.println(warning);
		}
		
	}

}
