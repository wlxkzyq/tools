package tools.mygenerator.api.dom.xml;

import tools.mygenerator.api.dom.OutputUtilities;

/** 
* xml Document类
* @author 作者 : zyq
* 创建时间：2017年3月6日 下午5:45:43 
* @version 
*/
public class Document {
    private String publicId;

    private String systemId;

    private XmlElement rootElement;

    /**
     *  
     */
    public Document(String publicId, String systemId) {
        super();
        this.publicId = publicId;
        this.systemId = systemId;
    }

    public Document() {
        super();
    }

    /**
     * @return Returns the rootElement.
     */
    public XmlElement getRootElement() {
        return rootElement;
    }

    /**
     * @param rootElement
     *            The rootElement to set.
     */
    public void setRootElement(XmlElement rootElement) {
        this.rootElement = rootElement;
    }

    /**
     * @return Returns the publicId.
     */
    public String getPublicId() {
        return publicId;
    }

    /**
     * @return Returns the systemId.
     */
    public String getSystemId() {
        return systemId;
    }

    public String getFormattedContent() {
        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"); //$NON-NLS-1$

        if (publicId != null && systemId != null) {
            OutputUtilities.newLine(sb);
            sb.append("<!DOCTYPE "); //$NON-NLS-1$
            sb.append(rootElement.getName());
            sb.append(" PUBLIC \""); //$NON-NLS-1$
            sb.append(publicId);
            sb.append("\" \""); //$NON-NLS-1$
            sb.append(systemId);
            sb.append("\" >"); //$NON-NLS-1$
        }

        OutputUtilities.newLine(sb);
        sb.append(rootElement.getFormattedContent(0));

        return sb.toString();
    }
}
