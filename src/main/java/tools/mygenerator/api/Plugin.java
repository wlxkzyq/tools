package tools.mygenerator.api;

import java.util.List;
import java.util.Properties;

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

}
