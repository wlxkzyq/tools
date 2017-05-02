package tools.mygenerator.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import static tools.mygenerator.internal.util.messages.Messages.getString;

import tools.common.file.FileUtil;
import tools.mygenerator.config.Context;

/** 
* 私人MybatisGenerator
* @author 作者 : zyq
* 创建时间：2016年12月13日 下午8:38:23 
* @version 
*/
public class MyGenerator {
	
	private static final Logger logger=Logger.getLogger(MyGenerator.class);
	
	private Context context;
	
    private List<GeneratedJavaFile> generatedJavaFiles;

    private List<GeneratedXmlFile> generatedXmlFiles;

    private List<String> warnings;
    
    
    public MyGenerator(Context context,List<String> warnings){
    	super();
    	if(context==null){
    		throw new IllegalArgumentException(getString("RuntimeError.2"));
    	}else {
            this.context = context;
        }
    	
    	if (warnings == null) {
            this.warnings = new ArrayList<String>();
        } else {
            this.warnings = warnings;
        }
    	
    	generatedJavaFiles = new ArrayList<GeneratedJavaFile>();
        generatedXmlFiles = new ArrayList<GeneratedXmlFile>();
    }
    
    /**
     * 调用生成方法入口
     * @throws Exception 
     */
    public void generate() throws Exception{
        generatedJavaFiles.clear();
        generatedXmlFiles.clear();
        
        context.generateFiles(generatedJavaFiles, generatedXmlFiles, warnings);
        
        for (GeneratedJavaFile gjf : generatedJavaFiles) {
            File targetFile;
            String source;
            File directory = FileUtil.getDirectory(gjf
                    .getTargetProject(), gjf.getTargetPackage());
            targetFile = new File(directory, gjf.getFileName());
            if (targetFile.exists()) {
            	//文件已存在
            	if(context.isOverwriteEnabled()){
            		//覆盖
            		source = gjf.getFormattedContent();
                    warnings.add(getString("Warning.11", //$NON-NLS-1$
                            targetFile.getAbsolutePath()));
            	}else{
            		//添加新文件
            		source = gjf.getFormattedContent();
                    targetFile = FileUtil.getUniqueFileName(directory, gjf
                            .getFileName());
                    warnings.add(getString(
                            "Warning.2", targetFile.getAbsolutePath())); //$NON-NLS-1$
            	}
            }else{
            	//文件不存在
            	source = gjf.getFormattedContent();
            }
            FileUtil.writeFile(targetFile, source, gjf.getFileEncoding());
            logger.info("【"+targetFile+"】生成完毕！");
		}
        
        //生成xml文件
        for (GeneratedXmlFile gxf : generatedXmlFiles) {
            File targetFile;
            String source;
            
        	File directory = FileUtil.getDirectory(gxf
                    .getTargetProject(), gxf.getTargetPackage());
            targetFile = new File(directory, gxf.getFileName());
            if (targetFile.exists()) {
               
                if (context.isOverwriteEnabled()) {
                    source = gxf.getFormattedContent();
                    warnings.add(getString("Warning.11", //$NON-NLS-1$
                            targetFile.getAbsolutePath()));
                } else {
                    source = gxf.getFormattedContent();
                    targetFile = FileUtil.getUniqueFileName(directory, gxf
                            .getFileName());
                    warnings.add(getString(
                            "Warning.2", targetFile.getAbsolutePath())); //$NON-NLS-1$
                }
            } else {
                source = gxf.getFormattedContent();
            }
            
            FileUtil.writeFile(targetFile, source, "UTF-8");
            logger.info("【"+targetFile+"】生成完毕！");
		}
        //FileUtil.writeFile(file, content, fileEncoding);
    }

}
