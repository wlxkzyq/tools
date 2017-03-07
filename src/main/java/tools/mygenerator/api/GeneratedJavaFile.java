package tools.mygenerator.api;

import tools.mygenerator.api.dom.java.CompilationUnit;

/** 
* 生成java代码类
* @author 作者 : zyq
* 创建时间：2017年3月2日 下午3:26:25 
* @version 
*/
public class GeneratedJavaFile extends GeneratedFile{
    private CompilationUnit compilationUnit;
    private String fileEncoding;
    private JavaFormatter javaFormatter;

    /**
     * Default constructor
     */
    public GeneratedJavaFile(CompilationUnit compilationUnit,
            String targetProject,
            String fileEncoding,
            JavaFormatter javaFormatter) {
        super(targetProject);
        this.compilationUnit = compilationUnit;
        this.fileEncoding = fileEncoding;
        this.javaFormatter = javaFormatter;
    }

    public GeneratedJavaFile(CompilationUnit compilationUnit,
            String targetProject,
            JavaFormatter javaFormatter) {
        this(compilationUnit, targetProject, null, javaFormatter);
    }
    
    @Override
    public String getFormattedContent() {
        return javaFormatter.getFormattedContent(compilationUnit);
    }

    @Override
    public String getFileName() {
        return compilationUnit.getType().getShortName() + ".java"; //$NON-NLS-1$
    }

    public String getTargetPackage() {
        return compilationUnit.getType().getPackageName();
    }

    /**
     * This method is required by the Eclipse Java merger. If you are not
     * running in Eclipse, or some other system that implements the Java merge
     * function, you may return null from this method.
     * 
     * @return the CompilationUnit associated with this file, or null if the
     *         file is not mergeable.
     */
    public CompilationUnit getCompilationUnit() {
        return compilationUnit;
    }

    /**
     * A Java file is mergeable if the getCompilationUnit() method returns a
     * valid compilation unit.
     * 
     */
    @Override
    public boolean isMergeable() {
        return true;
    }

    public String getFileEncoding() {
        return fileEncoding;
    }

}
