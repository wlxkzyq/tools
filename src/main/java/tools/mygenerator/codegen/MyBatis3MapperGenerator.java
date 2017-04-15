package tools.mygenerator.codegen;

import static tools.mygenerator.api.dom.OutputUtilities.newLine;
import static tools.mygenerator.api.dom.OutputUtilities.xmlIndent;

import java.util.List;

import tools.common.StringUtils;
import tools.mygenerator.api.IntrospectedColumn;
import tools.mygenerator.api.dom.xml.Attribute;
import tools.mygenerator.api.dom.xml.Document;
import tools.mygenerator.api.dom.xml.TextElement;
import tools.mygenerator.api.dom.xml.XmlElement;
import tools.mygenerator.internal.util.JavaBeansUtil;

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
	//是否生成查询所有sql语句
	private boolean generateSelectAllEnable;
	//是否使用map形式的参数
	private boolean mapParamEnable;
	private void init(){
		tableAlias=this.getContext().getSqlMapGeneratorConfiguration().getProperty("tableAlias");
		if(!StringUtils.isEmpty(tableAlias)){
			tableAlias="";
		}else{
			tableAlias=tableAlias+".";
		}
		generateTableNameEnable=getProperty("generateTableNameEnable");
		generateColumnsEnable=getProperty("generateColumnsEnable");
		generateSelectAllEnable=getProperty("generateSelectAllEnable");
		generateInsertEnable=getProperty("generateInsertEnable");
		mapParamEnable=getProperty("mapParamEnable");
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
		
		
		//添加sql节点
		getTableName(answer);
		getSqlColumns(answer);
		getSelectAll(answer);
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
		context.getCommentGenerator().addComment(parent);
		XmlElement answer=new XmlElement("sql");
		//设置id
		Attribute idAttribute=new Attribute("id", "tableName");
		answer.addAttribute(idAttribute);
		
		TextElement text=new TextElement(getIntrospectedTable().getTableName().toLowerCase()
				+" "+tableAlias);
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
		context.getCommentGenerator().addComment(parent);
		XmlElement answer=new XmlElement("sql");
		//设置id
		Attribute idAttribute=new Attribute("id", "selectColumns");
		answer.addAttribute(idAttribute);
		//遍历设置字段
		StringBuilder content=new StringBuilder("");
		List<IntrospectedColumn> columns=getIntrospectedTable().getColumns();
		String columnName="";
		for (int i = 0; i < columns.size(); i++) {
			columnName=columns.get(i).getColumnName();
			xmlIndent(content, 2);
			content.append(tableAlias);
			content.append(columnName);
			content.append("  as  ");
			content.append(context.getNameConfirm().getBeanFieldName(columns.get(i)));
			content.append(",");
			newLine(content);
		}
		content.delete(content.length()-3,content.length()-1);
		TextElement text=new TextElement(content.toString());
		answer.addElement(text);
		
		parent.addElement(answer);
	}
	
	/**
	 * 获取查询所有sql语句
	 * @param parent
	 */
	protected void getSelectAll(XmlElement parent){
		if(!generateSelectAllEnable){
			return;
		}
		//设置注释
		context.getCommentGenerator().addComment(parent);
		XmlElement answer=new XmlElement("select");
		//设置id
		Attribute idAttribute=new Attribute("id", "select"+context.getNameConfirm().getBeanName(introspectedTable)+"List");
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
		StringBuilder content=new StringBuilder("");
		content.append("select");
		newLine(content);
		xmlIndent(content, 2);
		content.append("<include refid=\"selectColumns\"></include>");
		newLine(content);
		xmlIndent(content, 2);
		content.append("from");
		newLine(content);
		xmlIndent(content, 2);
		content.append("<include refid=\"tableName\"></include>");
		newLine(content);
		xmlIndent(content, 2);
		content.append("<where>");
		//TODO 
		//添加where条件
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
				.append(".")
				.append(column.getColumnName())
				.append(" = #{ ")
				.append(columnName)
				.append(" }");
			}
			newLine(content);
			xmlIndent(content, 4);
			content.append("</if>");
			
		}
		newLine(content);
		xmlIndent(content, 2);
		content.append("</where>");
		//添加内容节点
		TextElement text=new TextElement(content.toString());
		answer.addElement(text);
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
		context.getCommentGenerator().addComment(parent);
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
		String value=this.getContext().getSqlMapGeneratorConfiguration().getProperty(key);
		if(value==null||"Y".equals(value)){
			return true;
		}else{
			return false;
		}
	}
	
	protected void addComment(XmlElement xmlElement,StringBuilder content){
		xmlElement.addElement(new TextElement("<!--")); //$NON-NLS-1$
        xmlElement.addElement(new TextElement(content.toString()));
        xmlElement.addElement(new TextElement("-->")); //$NON-NLS-1$
	}
	public static void main(String[] args) {
		MyBatis3MapperGenerator m=new MyBatis3MapperGenerator();
		System.out.println(m.getDocument().getFormattedContent());
	}

}
