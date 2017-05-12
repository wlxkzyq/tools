package tools.mygenerator.codegen;

import static tools.mygenerator.internal.util.JavaBeansUtil.getCamelCaseString;
import static tools.mygenerator.internal.util.JavaBeansUtil.getGetterMethodName;
import static tools.mygenerator.internal.util.JavaBeansUtil.getSetterMethodName;
import static tools.mygenerator.internal.util.StringUtility.isTrue;

import java.util.List;
import java.util.Properties;

import tools.mygenerator.api.IntrospectedColumn;
import tools.mygenerator.api.dom.java.CompilationUnit;
import tools.mygenerator.api.dom.java.Field;
import tools.mygenerator.api.dom.java.FullyQualifiedJavaType;
import tools.mygenerator.api.dom.java.JavaVisibility;
import tools.mygenerator.api.dom.java.Method;
import tools.mygenerator.api.dom.java.Parameter;
import tools.mygenerator.api.dom.java.TopLevelClass;
import tools.mygenerator.config.ConstantConfig;
import tools.mygenerator.config.PropertyRegistry;
import tools.mygenerator.internal.NameConfirm;
import tools.mygenerator.internal.util.JavaBeansUtil;

/** 
* java类 生成器 抽象类
* @author 作者 : zyq
* 创建时间：2017年3月7日 上午10:58:36 
* @version 
*/
public abstract class AbstractJavaGenerator extends AbstractGenerator{
	public abstract List<CompilationUnit> getCompilationUnits();
	
	
    public static Method getGetter(Field field) {
        Method method = new Method();
        method.setName(getGetterMethodName(field.getName(), field
                .getType()));
        method.setReturnType(field.getType());
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder sb = new StringBuilder();
        sb.append("return "); //$NON-NLS-1$
        sb.append(field.getName());
        sb.append(';');
        method.addBodyLine(sb.toString());
        return method;
    }
	
    public Field getJavaBeansField(IntrospectedColumn introspectedColumn) {
    	String property = context.getNameConfirm().getBeanFieldName( introspectedColumn );
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(JavaBeansUtil.getFieldType(introspectedColumn));
        field.setName(property);
        context.getCommentGenerator().addFieldComment(field,
                introspectedTable, introspectedColumn);
        return field;
    }
    public Method getJavaBeansGetter(IntrospectedColumn introspectedColumn) {
        FullyQualifiedJavaType fqjt = JavaBeansUtil.getFieldType(introspectedColumn);
        String property = getCamelCaseString(introspectedColumn.getColumnName(),false);

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(fqjt);
        method.setName(getGetterMethodName(property, fqjt));
        context.getCommentGenerator().addGetterComment(method,
                introspectedTable, introspectedColumn);

        StringBuilder sb = new StringBuilder();
        sb.append("return "); //$NON-NLS-1$
        sb.append(property);
        sb.append(';');
        method.addBodyLine(sb.toString());

        return method;
    }

    public Method getJavaBeansSetter(IntrospectedColumn introspectedColumn) {
        FullyQualifiedJavaType fqjt = JavaBeansUtil.getFieldType(introspectedColumn);
        String property = getCamelCaseString(introspectedColumn.getColumnName(),false);

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(getSetterMethodName(property));
        method.addParameter(new Parameter(fqjt, property));
        context.getCommentGenerator().addSetterComment(method,
                introspectedTable, introspectedColumn);

        StringBuilder sb = new StringBuilder();
        if (isTrimStringsEnabled() && "String".equals(fqjt.getShortName())) {
            sb.append("this."); //$NON-NLS-1$
            sb.append(property);
            sb.append(" = "); //$NON-NLS-1$
            sb.append(property);
            sb.append(" == null ? null : "); //$NON-NLS-1$
            sb.append(property);
            sb.append(".trim();"); //$NON-NLS-1$
            method.addBodyLine(sb.toString());
        } else {
            sb.append("this."); //$NON-NLS-1$
            sb.append(property);
            sb.append(" = "); //$NON-NLS-1$
            sb.append(property);
            sb.append(';');
            method.addBodyLine(sb.toString());
        }

        return method;
    }
    
    /**
     * 自己添加
     * 根据数据库列获取FullyQualifiedJavaType
     * @param introspectedColumn
     * @return
     */
//    private FullyQualifiedJavaType getFieldType(IntrospectedColumn introspectedColumn){
//    	FullyQualifiedJavaType fqjt=new FullyQualifiedJavaType(
//    			ConstantConfig.columnMap.get(introspectedColumn.getTypeName().toLowerCase()));
//    	
//    	
//    	return fqjt;
//    }
    
    /**
     * 根据配置获取是否对字符串进行 trim() 方法
     * @return
     */
    public boolean isTrimStringsEnabled() {
        Properties properties = context
                .getJavaModelGeneratorConfiguration().getProperties();
        boolean rc = isTrue(properties
                .getProperty(PropertyRegistry.MODEL_GENERATOR_TRIM_STRINGS));
        return rc;
    }
    
    protected void addDefaultConstructor(TopLevelClass topLevelClass) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setConstructor(true);
        method.setName(topLevelClass.getType().getShortName());
        method.addBodyLine("super();"); //$NON-NLS-1$
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }
    
    public String getRootClass() {
        Properties properties = context
                .getJavaModelGeneratorConfiguration().getProperties();
        String rootClass = properties.getProperty(PropertyRegistry.ANY_ROOT_CLASS);

        return rootClass;
    }

}
