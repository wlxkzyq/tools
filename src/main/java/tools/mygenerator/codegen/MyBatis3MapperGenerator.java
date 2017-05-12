package tools.mygenerator.codegen;

import static tools.mygenerator.api.dom.OutputUtilities.newLine;
import static tools.mygenerator.api.dom.OutputUtilities.xmlIndent;

import java.util.List;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tools.mygenerator.api.IntrospectedColumn;
import tools.mygenerator.api.dom.java.FullyQualifiedJavaType;
import tools.mygenerator.api.dom.xml.Attribute;
import tools.mygenerator.api.dom.xml.Document;
import tools.mygenerator.api.dom.xml.TextElement;
import tools.mygenerator.api.dom.xml.XmlElement;
import tools.mygenerator.config.PropertyRegistry;
import tools.mygenerator.internal.NameConfirm;
import tools.mygenerator.internal.util.JavaBeansUtil;
import tools.pub.StringUtils;

/** 
* mybatis3 sqlMapper生成器
* @author 作者 : zyq
* 创建时间：2017年3月14日 下午5:59:08 
* @version 
*/
public class MyBatis3MapperGenerator extends AbstractXmlGenerator {
	//表别名
	private String tableAlias;
	//是否生成tableName Sql
	private boolean generateTableNameEnable;
	//是否生成数据库列column Sql
	private boolean generateColumnsEnable;
	//是否生成普通插入 Sql
	private boolean generateInsertEnable;
	//是否生成通过条件查询<select>语句
	private boolean generateSelectByConditionEnable;
	//是否生成通过主键查询<select>语句
	private boolean generateSelectByPrimaryKeyEnable;
	//是否使用map形式的参数
	private boolean mapParamEnable;
	//Mapper文件 <where>节点条件形式
	private String mapperWhereCondition;
	
	private Properties properties;
	
	private NameConfirm nameConfirm;
	private void init(){
		nameConfirm=this.getContext().getNameConfirm();
		properties=this.getContext().getSqlMapGeneratorConfiguration().getProperties();
		tableAlias=properties.getProperty("tableAlias");
		if(StringUtils.isEmpty(tableAlias)){
			tableAlias="";
		}else{
			tableAlias=tableAlias+".";
		}
		generateTableNameEnable=getProperty("generateTableNameEnable");
		generateColumnsEnable=getProperty("generateColumnsEnable");
		generateSelectByConditionEnable=getProperty("generateSelectByConditionEnable");
		generateSelectByPrimaryKeyEnable=getProperty("generateSelectByPrimaryKeyEnable");
		generateInsertEnable=getProperty("generateInsertEnable");
		mapParamEnable=getProperty("mapParamEnable");
		mapperWhereCondition=properties.getProperty("mapperWhereCondition");
	}

	@Override
	public Document getDocument() {
		init();
		Document document = new Document(
                XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,
                XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
        document.setRootElement(getSqlMapElement());

        if (!context.getPluginAggregator().sqlMapDocumentGenerated(document,
                introspectedTable)) {
            document = null;
        }
//		XmlElement root=new XmlElement("Mapper");
//		document.setRootElement(root);
//		XmlElement child=new XmlElement("sql");
//		root.addElement(child);
        return document;
	}
	
	
	/**
	 * 生成xml根节点
	 * @return
	 */
	protected XmlElement getSqlMapElement() {
		XmlElement answer=new XmlElement("mapper");
		String namespace=context.getSqlMapGeneratorConfiguration().getTargetPackage()+
				JavaBeansUtil.getCamelCaseString(introspectedTable.getTableName(), true);
		answer.addAttribute(new Attribute("namespace", //$NON-NLS-1$
                namespace));
		context.getCommentGenerator().addRootComment(answer);
		//添加模糊查询模板
		StringBuilder likeString=new StringBuilder();
		likeString.append("根据需求自己加检索条件");
		newLine(likeString);
		xmlIndent(likeString, 2);
		likeString.append("字段1 LIKE CONCAT(CONCAT('%', #{pd.keywords},'%')");
		addComment(answer, likeString);
		//添加日期比较模板
		StringBuilder dateString=new StringBuilder();
		dateString.append("根据需求自己加检索条件（mysql）");
		newLine(dateString);
		xmlIndent(dateString, 2);
		dateString.append("AND UNIX_TIMESTAMP(tp.accepttime) <=  UNIX_TIMESTAMP(#{acceptTimeEnd})");
		addComment(answer, dateString);
		
		
		//添加表名节点
		getTableName(answer);
		//添加所有列节点
		getSqlColumns(answer);
		//添加根据条件查询
		getSelectByCondition(answer);
		//添加根据第一个主键查询
		getSelectByPrimaryKey(answer);
		//添加插入
		getInsert(answer);
		return answer;
	}
	
	/**
	 * 获取表名
	 * @param parent	父节点
	 */
	protected void getTableName(XmlElement parent){
		if(!generateTableNameEnable){
			return;
		}
		//设置注释
		context.getCommentGenerator().addComment(parent,"表名");
		XmlElement answer=new XmlElement("sql");
		//设置id
		Attribute idAttribute=new Attribute("id", "tableName");
		answer.addAttribute(idAttribute);
		String alias=StringUtils.isEmpty(tableAlias)?"":tableAlias.substring(0, tableAlias.length()-1);
		TextElement text=new TextElement(getIntrospectedTable().getTableName().toLowerCase()
				+" "+alias);
		answer.addElement(text);
		
		parent.addElement(answer);
	}
	
	/**
	 * 获取所有查询列
	 * @param parent
	 */
	protected void getSqlColumns(XmlElement parent){
		if(!generateColumnsEnable){
			return;
		}
		//设置注释
		context.getCommentGenerator().addComment(parent,"所有列");
		XmlElement answer=new XmlElement("sql");
		//设置id
		Attribute idAttribute=new Attribute("id", "selectColumns");
		answer.addAttribute(idAttribute);
		//遍历设置字段
		
		List<IntrospectedColumn> columns=getIntrospectedTable().getColumns();
		String columnName="";
		for (int i = 0; i < columns.size()-1; i++) {
			StringBuilder content=new StringBuilder("");
			columnName=columns.get(i).getColumnName();
			content.append(tableAlias);
			content.append(columnName);
			content.append("  as  ");
			content.append(context.getNameConfirm().getBeanFieldName(columns.get(i)));
			content.append(",");
			answer.addElement(new TextElement(content.toString()));
		}
		StringBuilder content=new StringBuilder("");
		columnName=columns.get(columns.size()-1).getColumnName();
		content.append(tableAlias);
		content.append(columnName);
		content.append("  as  ");
		content.append(context.getNameConfirm().getBeanFieldName(columns.get(columns.size()-1)));
		answer.addElement(new TextElement(content.toString()));
		
		parent.addElement(answer);
	}
	
	/**
	 * 获取通过条件查询
	 * @param parent
	 */
	protected void getSelectByCondition(XmlElement parent){
		if(!generateSelectByConditionEnable){
			return;
		}
		//设置注释
		context.getCommentGenerator().addComment(parent,"通过条件查询");
		XmlElement answer=new XmlElement("select");
		//设置id
		Attribute idAttribute=new Attribute("id",
				properties.getProperty(PropertyRegistry.MAPPER_SELECTBYCONDITION_ID_PREFIX)+
				context.getNameConfirm().getBeanName(introspectedTable)+
				properties.getProperty(PropertyRegistry.MAPPER_SELECTBYCONDITION_ID_SUFFIX));
		answer.addAttribute(idAttribute);
		//设置入参和返回的类
		Attribute parameterTypeAttribute;
		Attribute resultTypeAttribute;
		if(mapParamEnable){
			parameterTypeAttribute=new Attribute("parameterType", "java.util.Map");
			resultTypeAttribute=new Attribute("resultType", "java.util.Map");
		}else{
			String beanFullName=context.getJavaModelGeneratorConfiguration().getTargetPackage()+"."
					+context.getNameConfirm().getBeanName(introspectedTable);
			parameterTypeAttribute=new Attribute("parameterType", beanFullName);
			resultTypeAttribute=new Attribute("resultType", beanFullName);
		}
		answer.addAttribute(parameterTypeAttribute);
		answer.addAttribute(resultTypeAttribute);
		//添加查询所有节点内容
		answer.addElement(new TextElement("select"));
		answer.addElement(new TextElement("<include refid=\"selectColumns\"></include>"));
		answer.addElement(new TextElement("from"));
		answer.addElement(new TextElement("<include refid=\"tableName\"></include>"));
		//TODO 
		//添加where条件
		addWhereCondition(answer);

		parent.addElement(answer);
	}
	
	/**
	 * 获取通过主键查询
	 * @param parent
	 */
	protected void getSelectByPrimaryKey(XmlElement parent){
		if(!generateSelectByPrimaryKeyEnable){
			return;
		}
		//设置注释
		context.getCommentGenerator().addComment(parent,"通过主键查询");
		XmlElement answer=new XmlElement("select");
		//设置id
		Attribute idAttribute=new Attribute("id",
				properties.getProperty(PropertyRegistry.MAPPER_SELECTBYPRIMARYKEY_ID_PREFIX)+
				context.getNameConfirm().getBeanName(introspectedTable)+
				properties.getProperty(PropertyRegistry.MAPPER_SELECTBYPRIMARYKEY_ID_SUFFIX));
		answer.addAttribute(idAttribute);
		//设置入参和返回的类
		Attribute parameterTypeAttribute;
		Attribute resultTypeAttribute;
		IntrospectedColumn pkColumn=introspectedTable.getFirstPrimaryKey();
		FullyQualifiedJavaType fieldType = JavaBeansUtil.getFieldType(pkColumn);
		if(mapParamEnable){
			parameterTypeAttribute=new Attribute("parameterType", fieldType.getFullyQualifiedName());
			resultTypeAttribute=new Attribute("resultType", "java.util.Map");
		}else{
			String beanFullName=context.getJavaModelGeneratorConfiguration().getTargetPackage()+"."
					+context.getNameConfirm().getBeanName(introspectedTable);
			parameterTypeAttribute=new Attribute("parameterType", fieldType.getFullyQualifiedName());
			resultTypeAttribute=new Attribute("resultType", beanFullName);
		}
		answer.addAttribute(parameterTypeAttribute);
		answer.addAttribute(resultTypeAttribute);
		//添加查询所有节点内容
		StringBuilder content=new StringBuilder("");
		answer.addElement(new TextElement("select"));
		answer.addElement(new TextElement("<include refid=\"selectColumns\"></include>"));
		answer.addElement(new TextElement("from"));
		answer.addElement(new TextElement("<include refid=\"tableName\"></include>"));
		answer.addElement(new TextElement("where"));
		
		content.append(getTableAliasColumnName(pkColumn.getColumnName()))
		.append(" = #{ value }");
		answer.addElement(new TextElement("  "+content.toString()));

		parent.addElement(answer);
	}
	
	/**
	 * 获取普通插入
	 * @param parent
	 */
	protected void getInsert(XmlElement parent){
		if(!generateInsertEnable){
			return;
		}
		//设置注释
		context.getCommentGenerator().addComment(parent,"普通插入");
		XmlElement answer=new XmlElement("insert");
		//设置id
		Attribute idAttribute=new Attribute("id", "insert");
		answer.addAttribute(idAttribute);
		//设置入参和返回的类
		Attribute parameterTypeAttribute;
		if(mapParamEnable){
			parameterTypeAttribute=new Attribute("parameterType", "java.util.Map");
		}else{
			String beanFullName=context.getJavaModelGeneratorConfiguration().getTargetPackage()+"."
					+context.getNameConfirm().getBeanName(introspectedTable);
			parameterTypeAttribute=new Attribute("parameterType", beanFullName);
		}
		answer.addAttribute(parameterTypeAttribute);
		//遍历设置字段
		StringBuilder content=new StringBuilder("");
		content.append("insert into ");
		newLine(content);
		xmlIndent(content, 2);
		content.append(getIntrospectedTable().getTableName());
		newLine(content);
		
		//定义表格columns的字符串
		StringBuilder sbColumns=new StringBuilder("(");
		//定义values的字符串
		StringBuilder sbValues=new StringBuilder("(");
		newLine(sbColumns);
		newLine(sbValues);
		List<IntrospectedColumn> columns=getIntrospectedTable().getColumns();
		String columnName="";
		IntrospectedColumn column=null;
		for (int i = 0; i < columns.size(); i++) {
			column=columns.get(i);
			columnName=column.getColumnName();
			xmlIndent(sbColumns, 2);
			sbColumns.append(columnName);
			sbColumns.append(",");
			newLine(sbColumns);
			
			xmlIndent(sbValues, 2);
			sbValues.append("#{");
			sbValues.append(context.getNameConfirm().getBeanFieldName(column));
			sbValues.append("},");
			newLine(sbValues);
		}
		sbColumns.delete(sbColumns.length()-3,sbColumns.length()-1);
		sbValues.delete(sbValues.length()-3,sbValues.length()-1);
		newLine(sbColumns);
		newLine(sbValues);
		sbColumns.append(")");
		sbValues.append(")");
		content.append(sbColumns);
		content.append(" values ");
		content.append(sbValues);
		TextElement text=new TextElement(content.toString());
		answer.addElement(text);
		
		parent.addElement(answer);
	}
	
	/**
	 * 获取配置信息（只获取值为是否类型的）
	 * @param key
	 * @return
	 */
	protected boolean getProperty(String key){
		String value=properties.getProperty(key);
		if(value==null||"Y".equals(value)){
			return true;
		}else{
			return false;
		}
	}
	
	protected void addComment(XmlElement xmlElement,StringBuilder content){
		xmlElement.addElement(new TextElement("<!--")); //$NON-NLS-1$
        xmlElement.addElement(new TextElement("  "+content.toString()));
        xmlElement.addElement(new TextElement("-->")); //$NON-NLS-1$
	}
	
	/**
	 * 根据{@code mapperWhereCondition}配置添加where条件
	 * @param answer
	 */
	private void addWhereCondition(XmlElement answer){
		if(PropertyRegistry.MAPPER_WHERE_CONDITION_NULL.equals(mapperWhereCondition)){
			return;
		}
		if(PropertyRegistry.MAPPER_WHERE_CONDITION_DEFAULT.equals(mapperWhereCondition)){
			answer.addElement(new TextElement("<where>"));
			StringBuilder content=new StringBuilder("");
			IntrospectedColumn column=null;
			List<IntrospectedColumn> columns=introspectedTable.getColumns();
			String columnName="";
			for (int i = 0; i < columns.size(); i++) {
				column=columns.get(i);
				columnName=context.getNameConfirm().getBeanFieldName(column);
				newLine(content);
				xmlIndent(content, 4);
				content.append("<if test=\""+columnName+" !=null and "+columnName+"!=''\">");
				newLine(content);
				xmlIndent(content, 6);
				if(tableAlias==null||tableAlias.length()<1){
					content.append("and ")
					.append(column.getColumnName())
					.append(" = #{ ")
					.append(columnName)
					.append(" }");
				}else{
					content.append("and ")
					.append(tableAlias)
					.append(column.getColumnName())
					.append(" = #{ ")
					.append(columnName)
					.append(" }");
				}
				newLine(content);
				xmlIndent(content, 4);
				content.append("</if>");
				
			}
			//添加内容节点
			TextElement text=new TextElement(content.toString());
			answer.addElement(text);
			answer.addElement(new TextElement("</where>"));
		}
		
		if(PropertyRegistry.MAPPER_WHERE_CONDITION_DYNAMIC.equals(mapperWhereCondition)){
			XmlElement where=new XmlElement("where");
			XmlElement foreach=new XmlElement("foreach");
			foreach.addAttribute(new Attribute("collection", "condition.criteria"));
			foreach.addAttribute(new Attribute("item", "condition.criterion"));
			XmlElement choose=new XmlElement("choose");
			
			XmlElement whenNoValue=new XmlElement("when");
			whenNoValue.addAttribute(new Attribute("test", "criterion.noValue"));
			whenNoValue.addElement(new TextElement("and ${criterion.condition}"));
			choose.addElement(whenNoValue);
			
			XmlElement whenSingleValue=new XmlElement("when");
			whenSingleValue.addAttribute(new Attribute("test", "criterion.singleValue"));
			whenSingleValue.addElement(new TextElement("and ${criterion.condition} #{criterion.value}"));
			choose.addElement(whenSingleValue);
			
			XmlElement whenBetweenValue=new XmlElement("when");
			whenBetweenValue.addAttribute(new Attribute("test", "criterion.betweenValue"));
			whenBetweenValue.addElement(new TextElement("and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}"));
			choose.addElement(whenBetweenValue);
			
			XmlElement whenListValue=new XmlElement("when");
			whenListValue.addAttribute(new Attribute("test", "criterion.listValue"));
			whenListValue.addElement(new TextElement("and ${criterion.condition} "));
			XmlElement listValue=new XmlElement("foreach");
			listValue.addAttribute(new Attribute("collection", "criterion.value"));
			listValue.addAttribute(new Attribute("item", "listItem"));
			listValue.addAttribute(new Attribute("open", "("));
			listValue.addAttribute(new Attribute("close", ")"));
			listValue.addAttribute(new Attribute("separator", ","));
			listValue.addElement(new TextElement("#{listItem}"));
			whenListValue.addElement(listValue);
			choose.addElement(whenListValue);
			
			foreach.addElement(choose);
			where.addElement(foreach);
			answer.addElement(where);
		}
		if(PropertyRegistry.MAPPER_WHERE_CONDITION_STATIC.equals(mapperWhereCondition)){
			JSONObject conditin=this.getContext().getSqlMapGeneratorConfiguration().getCondition();
			if(conditin.getBooleanValue("valid")){
				XmlElement where=new XmlElement("where");
				
				answer.addElement(where);
				
				JSONArray criteria=conditin.getJSONArray("criteria");
				JSONObject temp=null;
				for (int i = 0; i < criteria.size(); i++) {
					temp=criteria.getJSONObject(i);
					String condition=temp.getString("condition");
					String simpleCondition=temp.getString("simpleCondition");
					String value=temp.getString("value");
					String secondValue=temp.getString("secondValue");
					String property=temp.getString("property");
					Boolean noValue=temp.getBoolean("noValue");
					Boolean singleValue=temp.getBoolean("singleValue");
					Boolean betweenValue=temp.getBoolean("betweenValue");
					Boolean listValue=temp.getBoolean("listValue");
					String typeHandler=temp.getString("typeHandler");
					
					XmlElement if1=new XmlElement("if");
					String beanPro=nameConfirm.getBeanFieldName(property);
					String testValue=beanPro+" != null && "+beanPro+" !=''";
					if1.addAttribute(new Attribute("test",testValue));
					StringBuilder text=new StringBuilder();
					if(noValue){
						text.append("and ").append(getTableAliasColumnName(property)+simpleCondition);
						if1.addElement(new TextElement(text.toString()));
					}
					if(singleValue){
						text.append("and ").append(getTableAliasColumnName(property)+simpleCondition).append(" #{").append("params."+property).append("}");
						if1.addElement(new TextElement(text.toString()));
					}
					if(betweenValue){
						text.append("and ").append(getTableAliasColumnName(property)+simpleCondition).append(" #{").append("params."+property).append("Start} ")
						.append("and ").append(" #{").append("params."+property).append("End} ");
						if1.addElement(new TextElement(text.toString()));
					}
					if(listValue){
						text.append("and ").append(condition);
						if1.addElement(new TextElement(text.toString()));
						XmlElement listValue2=new XmlElement("foreach");
						listValue2.addAttribute(new Attribute("collection", "params."+property));
						listValue2.addAttribute(new Attribute("item", "listItem"));
						listValue2.addAttribute(new Attribute("open", "("));
						listValue2.addAttribute(new Attribute("close", ")"));
						listValue2.addAttribute(new Attribute("separator", ","));
						listValue2.addElement(new TextElement("#{listItem}"));
						if1.addElement(listValue2);
						
					}
					where.addElement(if1);
					//TODO 获取其它条件信息
				}
			}
		}
		
	}
	private String getTableAliasColumnName(String columnName){
		if(tableAlias==null||tableAlias.length()<1){
			return columnName;
		}else{
			StringBuilder content=new StringBuilder(tableAlias);
			content.append(columnName);
			return content.toString();
		}
	}
	public static void main(String[] args) {
		MyBatis3MapperGenerator m=new MyBatis3MapperGenerator();
		System.out.println(m.getDocument().getFormattedContent());
	}

}
