package tools.mygenerator.codegen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static tools.mygenerator.internal.util.JavaBeansUtil.getCamelCaseString;
import tools.mygenerator.api.CommentGenerator;
import tools.mygenerator.api.IntrospectedColumn;
import tools.mygenerator.api.dom.java.CompilationUnit;
import tools.mygenerator.api.dom.java.Field;
import tools.mygenerator.api.dom.java.FullyQualifiedJavaType;
import tools.mygenerator.api.dom.java.JavaVisibility;
import tools.mygenerator.api.dom.java.Method;
import tools.mygenerator.api.dom.java.TopLevelClass;
import tools.mygenerator.config.JavaModelGeneratorConfiguration;

/** 
* java实体类 生成器 
* @author 作者 : zyq
* 创建时间：2017年3月7日 下午7:16:28 
* @version 
*/
public class JavaEntityGenerator extends AbstractJavaGenerator{

	@Override
	public List<CompilationUnit> getCompilationUnits() {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration=
				context.getJavaModelGeneratorConfiguration();
		String targetPackage=javaModelGeneratorConfiguration.getTargetPackage();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(targetPackage+"."+
				getCamelCaseString(introspectedTable.getTableName(),true));
		TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);
        
        FullyQualifiedJavaType superClass = getSuperClass();
        if (superClass != null) {
            topLevelClass.setSuperClass(superClass);
            topLevelClass.addImportedType(superClass);
        }
        
        List<IntrospectedColumn> introspectedColumns=introspectedTable.getColumns();
        
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
        	Field field = getJavaBeansField(introspectedColumn);
        	topLevelClass.addField(field);
            topLevelClass.addImportedType(field.getType());
            
            Method getMethod=getJavaBeansGetter(introspectedColumn);
            topLevelClass.addMethod(getMethod);
            
            Method setMethod=getJavaBeansSetter(introspectedColumn);
            topLevelClass.addMethod(setMethod);
		}
        
        List<CompilationUnit> answer= new ArrayList<CompilationUnit>();
        answer.add(topLevelClass);
		return answer;
	}
	
	/**
	 * 获取父类
	 * @return
	 */
    private FullyQualifiedJavaType getSuperClass() {
        FullyQualifiedJavaType superClass;
        String rootClass = getRootClass();
        if (rootClass != null) {
            superClass = new FullyQualifiedJavaType(rootClass);
        } else {
            superClass = null;
        }

        return superClass;
    }

}
