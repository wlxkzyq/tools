package tools.mygenerator.api.dom.xml;
/** 
* xml属性
* @author 作者 : zyq
* 创建时间：2017年3月6日 下午5:44:33 
* @version 
*/
public class Attribute {
    private String name;
    private String value;

    /**
     * 
     */
    public Attribute(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    public String getFormattedContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("=\""); //$NON-NLS-1$
        sb.append(value);
        sb.append('\"');

        return sb.toString();
    }
}
