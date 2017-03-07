package tools.mygenerator.api.dom.xml;
/** 
* xml 节点抽象类
* @author 作者 : zyq
* 创建时间：2017年3月6日 下午5:46:50 
* @version 
*/
public abstract class Element {

    /**
     * 
     */
    public Element() {
        super();
    }

    public abstract String getFormattedContent(int indentLevel);
}
