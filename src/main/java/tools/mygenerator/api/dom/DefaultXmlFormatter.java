package tools.mygenerator.api.dom;

import tools.mygenerator.api.XmlFormatter;
import tools.mygenerator.api.dom.xml.Document;
import tools.mygenerator.config.Context;

/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月9日 下午9:06:20 
* @version 
*/
public class DefaultXmlFormatter implements XmlFormatter{
    protected Context context;
    
    public String getFormattedContent(Document document) {
        return document.getFormattedContent();
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
