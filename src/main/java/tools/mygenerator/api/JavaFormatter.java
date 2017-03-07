package tools.mygenerator.api;

import tools.mygenerator.api.dom.java.CompilationUnit;
import tools.mygenerator.config.Context;

/**
*  
* @author 作者 : zyq
* 创建时间：2017年3月2日 下午3:28:18 
* @version 
*/
public interface JavaFormatter {
	void setContext(Context context);
    String getFormattedContent(CompilationUnit compilationUnit);
}
