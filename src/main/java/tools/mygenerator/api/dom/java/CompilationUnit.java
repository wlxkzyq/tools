package tools.mygenerator.api.dom.java;

import java.util.List;
import java.util.Set;


/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月2日 下午3:33:21 
* @version 
*/
public interface CompilationUnit {
	   String getFormattedContent();

	    Set<FullyQualifiedJavaType> getImportedTypes();
	    
	    Set<String> getStaticImports();

	    FullyQualifiedJavaType getSuperClass();

	    boolean isJavaInterface();

	    boolean isJavaEnumeration();

	    Set<FullyQualifiedJavaType> getSuperInterfaceTypes();

	    FullyQualifiedJavaType getType();

	    void addImportedType(FullyQualifiedJavaType importedType);

	    void addImportedTypes(Set<FullyQualifiedJavaType> importedTypes);
	    
	    void addStaticImport(String staticImport);
	    
	    void addStaticImports(Set<String> staticImports);

	    /**
	     * Comments will be written at the top of the file as is, we do not append
	     * any start or end comment characters.
	     * 
	     * Note that in the Eclipse plugin, file comments will not be merged.
	     * 
	     * @param commentLine
	     */
	    void addFileCommentLine(String commentLine);

	    List<String> getFileCommentLines();
}
