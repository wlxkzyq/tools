package tools.mygenerator.api;

import java.util.ArrayList;
import java.util.List;

import static tools.mygenerator.internal.util.messages.Messages.getString;
import tools.mygenerator.config.Context;

/** 
* 私人MybatisGenerator
* @author 作者 : zyq
* 创建时间：2016年12月13日 下午8:38:23 
* @version 
*/
public class MyGenerator {
	
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
     */
    public void generate(){
        generatedJavaFiles.clear();
        generatedXmlFiles.clear();
    }

}
