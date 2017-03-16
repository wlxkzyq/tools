package tools.mygenerator.api;

import java.util.List;
import java.util.Properties;

import tools.mygenerator.api.dom.java.TopLevelClass;
import tools.mygenerator.api.dom.xml.Document;
import tools.mygenerator.config.Context;

/** 
* 插件接口 
* @author 作者 : zyq
* 创建时间：2017年3月9日 下午4:03:56 
* @version 
*/
public interface Plugin {
	
    /**
     * Set the context under which this plugin is running
     * 
     * @param context
     */
    void setContext(Context context);
    
    /**
     * Set properties from the plugin configuration
     * 
     * @param properties
     */
    void setProperties(Properties properties);
    
    /**
     * This method is called just before the getGeneratedXXXFiles methods are
     * called on the introspected table. Plugins can implement this method to
     * override any of the default attributes, or change the results of database
     * introspection, before any code generation activities occur. Attributes
     * are listed as static Strings with the prefix ATTR_ in IntrospectedTable.
     * <p>
     * A good example of overriding an attribute would be the case where a user
     * wanted to change the name of one of the generated classes, change the
     * target package, or change the name of the generated SQL map file.
     * <p>
     * <b>Warning:</b> Anything that is listed as an attribute should not be
     * changed by one of the other plugin methods. For example, if you want to
     * change the name of a generated example class, you should not simply
     * change the Type in the <code>modelExampleClassGenerated()</code> method.
     * If you do, the change will not be reflected in other generated artifacts.
     * 
     * @param introspectedTable
     */
    void initialized(IntrospectedTable introspectedTable);
    
    /**
     * This method is called after all the setXXX methods are called, but before
     * any other method is called. This allows the plugin to determine whether
     * it can run or not. For example, if the plugin requires certain properties
     * to be set, and the properties are not set, then the plugin is invalid and
     * will not run.
     * 
     * @param warnings
     *            add strings to this list to specify warnings. For example, if
     *            the plugin is invalid, you should specify why. Warnings are
     *            reported to users after the completion of the run.
     * @return true if the plugin is in a valid state. Invalid plugins will not
     *         be called
     */
    boolean validate(List<String> warnings);
    /**
     * This method can be used to generate any additional Java file needed by
     * your implementation. This method is called once, after all other Java
     * files have been generated.
     * 
     * @return a List of GeneratedJavaFiles - these files will be saved
     *         with the other files from this run.
     */
    List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles();

    /**
     * This method can be used to generate additional Java files needed by your
     * implementation that might be related to a specific table. This method is
     * called once for every table in the configuration.
     * 
     * @param introspectedTable
     *            The class containing information about the table as
     *            introspected from the database
     * @return a List of GeneratedJavaFiles - these files will be saved
     *         with the other files from this run.
     */
    List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(
            IntrospectedTable introspectedTable);

    /**
     * This method can be used to generate any additional XML file needed by
     * your implementation. This method is called once, after all other XML
     * files have been generated.
     * 
     * @return a List of GeneratedXmlFiles - these files will be saved
     *         with the other files from this run.
     */
    List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles();

    /**
     * This method can be used to generate additional XML files needed by your
     * implementation that might be related to a specific table. This method is
     * called once for every table in the configuration.
     * 
     * @param introspectedTable
     *            The class containing information about the table as
     *            introspected from the database
     * @return a List of GeneratedXmlFiles - these files will be saved
     *         with the other files from this run.
     */
    List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(
            IntrospectedTable introspectedTable);
    
    /**
     * 生成java实体类，在类所有field，method添加完之后执行该方法
     * @param topLevelClass
     * @param table
     * @return
     */
    public boolean modelClassGenerator(TopLevelClass topLevelClass,IntrospectedTable table);

    
    /**
     * 生成sqlMapperDocument时调用插件
     * @param document
     * @param introspectedTable
     * @return
     */
    public boolean sqlMapDocumentGenerated(Document document,IntrospectedTable introspectedTable);
    
    /**
     * 生成SQLMapperFile文件时调用插件
     * @param generatedXmlFile
     * @param introspectedTable
     * @return
     */
    public boolean sqlMapGenerated(GeneratedXmlFile generatedXmlFile ,IntrospectedTable introspectedTable);

}
