package tools.mygenerator.api.dom;

import tools.mygenerator.api.JavaFormatter;
import tools.mygenerator.api.dom.java.CompilationUnit;
import tools.mygenerator.config.Context;

/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月7日 下午8:29:06 
* @version 
*/
public class DefaultJavaFormatter implements JavaFormatter {
    protected Context context;
    
    public String getFormattedContent(CompilationUnit compilationUnit) {
        return compilationUnit.getFormattedContent();
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
