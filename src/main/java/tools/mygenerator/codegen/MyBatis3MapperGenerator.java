package tools.mygenerator.codegen;

import tools.mygenerator.api.dom.xml.Attribute;
import tools.mygenerator.api.dom.xml.Document;
import tools.mygenerator.api.dom.xml.XmlElement;
import tools.mygenerator.internal.util.JavaBeansUtil;

/** 
* mybatis3 sqlMapper生成器
* @author 作者 : zyq
* 创建时间：2017年3月14日 下午5:59:08 
* @version 
*/
public class MyBatis3MapperGenerator extends AbstractXmlGenerator {

	@Override
	public Document getDocument() {
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
	
	
	protected XmlElement getSqlMapElement() {
		XmlElement answer=new XmlElement("mapper");
		String namespace=context.getSqlMapGeneratorConfiguration().getTargetPackage()+
				JavaBeansUtil.getCamelCaseString(introspectedTable.getTableName(), true);
		answer.addAttribute(new Attribute("namespace", //$NON-NLS-1$
                namespace));
		context.getCommentGenerator().addRootComment(answer);
		
		
		return answer;
	}
	
	public static void main(String[] args) {
		MyBatis3MapperGenerator m=new MyBatis3MapperGenerator();
		System.out.println(m.getDocument().getFormattedContent());
	}

}
