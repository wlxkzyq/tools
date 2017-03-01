package tools.mygenerator.api.dom.java;
/** 
* java范围修饰符
* @author 作者 : zyq
* 创建时间：2017年3月1日 上午11:28:44 
* @version 
*/
public enum JavaVisibility {
	PUBLIC("public "), //$NON-NLS-1$
    PRIVATE("private "), //$NON-NLS-1$
    PROTECTED("protected "), //$NON-NLS-1$
    DEFAULT(""); //$NON-NLS-1$

    private String value;

    private JavaVisibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
