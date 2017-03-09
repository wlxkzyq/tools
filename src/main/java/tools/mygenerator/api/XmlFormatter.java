package tools.mygenerator.api;

import tools.mygenerator.api.dom.xml.Document;
import tools.mygenerator.config.Context;

/** 
* xml格式化接口
* @author 作者 : zyq
* 创建时间：2017年3月9日 下午9:05:10 
* @version 
*/
public interface XmlFormatter {
	void setContext(Context context);
    String getFormattedContent(Document document);
}
