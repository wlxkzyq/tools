package tools.mygenerator.api;
/** 
* 生成文件抽象类 
* @author 作者 : zyq
* 创建时间：2017年3月2日 下午3:24:21 
* @version 
*/

public abstract class GeneratedFile {
    protected String targetProject;

    /**
	 *  
	 */
    public GeneratedFile(String targetProject) {
        super();
        this.targetProject = targetProject;
    }

    /**
     * This method returns the entire contents of the generated file. Clients
     * can simply save the value returned from this method as the file contents.
     * Subclasses such as @see org.mybatis.generator.api.GeneratedJavaFile offer
     * more fine grained access to file parts, but still implement this method
     * in the event that the entire contents are desired.
     * 
     * @return Returns the content.
     */
    public abstract String getFormattedContent();

    /**
     * Get the file name (without any path). Clients should use this method to
     * determine how to save the results.
     * 
     * @return Returns the file name.
     */
    public abstract String getFileName();

    /**
     * Gets the target project. Clients can call this method to determine how to
     * save the results.
     * 
     * @return the target project
     */
    public String getTargetProject() {
        return targetProject;
    }

    /**
     * Get the target package for the file. Clients should use this method to
     * determine how to save the results.
     * 
     * @return Returns the target project.
     */
    public abstract String getTargetPackage();

    @Override
    public String toString() {
        return getFormattedContent();
    }

    public abstract boolean isMergeable();
} 
