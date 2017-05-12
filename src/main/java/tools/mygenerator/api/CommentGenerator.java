package tools.mygenerator.api;

import java.util.Properties;

import tools.mygenerator.api.dom.java.CompilationUnit;
import tools.mygenerator.api.dom.java.Field;
import tools.mygenerator.api.dom.java.InnerClass;
import tools.mygenerator.api.dom.java.InnerEnum;
import tools.mygenerator.api.dom.java.Method;
import tools.mygenerator.api.dom.xml.XmlElement;


/** 
* 注释生成接口
* @author 作者 : zyq
* 创建时间：2017年3月6日 下午5:41:28 
* @version 
*/
public interface CommentGenerator {

    /**
     * Adds properties for this instance from any properties configured in the
     * CommentGenerator configuration.
     * 
     * This method will be called before any of the other methods.
     * 
     * @param properties
     *            All properties from the configuration
     */
    void addConfigurationProperties(Properties properties);

    /**
     * This method should add a Javadoc comment to the specified field. The
     * field is related to the specified table and is used to hold the value of
     * the specified column.
     * <p>
     * 
     * <b>Important:</b> This method should add a the nonstandard JavaDoc tag
     * "@mbggenerated" to the comment. Without this tag, the Eclipse based Java
     * merge feature will fail.
     * 
     * @param field
     * @param introspectedTable
     * @param introspectedColumn
     */
    public void addFieldComment(Field field,
            IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn);

    public void addFieldComment(Field field, IntrospectedTable introspectedTable);

    public void addClassComment(InnerClass innerClass,
            IntrospectedTable introspectedTable);

    public void addClassComment(InnerClass innerClass,
            IntrospectedTable introspectedTable, boolean markAsDoNotDelete);

    public void addEnumComment(InnerEnum innerEnum,
            IntrospectedTable introspectedTable);

    public void addGetterComment(Method method,
            IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn);

    public void addSetterComment(Method method,
            IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn);

    public void addGeneralMethodComment(Method method,
            IntrospectedTable introspectedTable);

    /**
     * This method is called to add a file level comment to a generated java
     * file. This method could be used to add a general file comment (such as a
     * copyright notice). However, note that the Java file merge function in
     * Eclipse does not deal with this comment. If you run the generator
     * repeatedly, you will only retain the comment from the initial run.
     * <p>
     * 
     * The default implementation does nothing.
     * 
     * @param compilationUnit
     */
    public void addJavaFileComment(CompilationUnit compilationUnit);

    /**
     * This method should add a suitable comment as a child element of the
     * specified xmlElement to warn users that the element was generated and is
     * subject to regeneration.
     * 
     * @param xmlElement
     */
    public void addComment(XmlElement xmlElement);
    
    /**
     * 向xml添加指定的注释
     * @param xmlElement
     * @param content	注释的内容
     */
    public void addComment(XmlElement xmlElement,String content);

    /**
     * This method is called to add a comment as the first child of the root
     * element. This method could be used to add a general file comment (such as
     * a copyright notice). However, note that the XML file merge function does
     * not deal with this comment. If you run the generator repeatedly, you will
     * only retain the comment from the initial run.
     * <p>
     * 
     * The default implementation does nothing.
     * 
     * @param rootElement
     */
    public void addRootComment(XmlElement rootElement);

}
