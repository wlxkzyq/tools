package tools.mygenerator.api.dom.xml;

import tools.mygenerator.api.dom.OutputUtilities;

/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月6日 下午6:00:41 
* @version 
*/
public class TextElement extends Element {
	   private String content;

	    /**
	     *  
	     */
	    public TextElement(String content) {
	        super();
	        this.content = content;
	    }

	    @Override
	    public String getFormattedContent(int indentLevel) {
	        StringBuilder sb = new StringBuilder();
	        OutputUtilities.xmlIndent(sb, indentLevel);
	        sb.append(content);
	        return sb.toString();
	    }

	    public String getContent() {
	        return content;
	    }
}
