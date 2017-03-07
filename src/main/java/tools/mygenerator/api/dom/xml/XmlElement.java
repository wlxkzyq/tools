package tools.mygenerator.api.dom.xml;

import java.util.ArrayList;
import java.util.List;

import tools.mygenerator.api.dom.OutputUtilities;

/** 
* xml 节点类
* @author 作者 : zyq
* 创建时间：2017年3月6日 下午5:49:19 
* @version 
*/
public class XmlElement extends Element{
    private List<Attribute> attributes;

    private List<Element> elements;

    private String name;

    /**
     *  
     */
    public XmlElement(String name) {
        super();
        attributes = new ArrayList<Attribute>();
        elements = new ArrayList<Element>();
        this.name = name;
    }
    
    /**
     * Copy constructor.  Not a truly deep copy, but close enough
     * for most purposes.
     * 
     * @param original
     */
    public XmlElement(XmlElement original) {
        super();
        attributes = new ArrayList<Attribute>();
        attributes.addAll(original.attributes);
        elements = new ArrayList<Element>();
        elements.addAll(original.elements);
        this.name = original.name;
    }

    /**
     * @return Returns the attributes.
     */
    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    /**
     * @return Returns the elements.
     */
    public List<Element> getElements() {
        return elements;
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public void addElement(int index, Element element) {
        elements.add(index, element);
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    @Override
    public String getFormattedContent(int indentLevel) {
        StringBuilder sb = new StringBuilder();

        OutputUtilities.xmlIndent(sb, indentLevel);
        sb.append('<');
        sb.append(name);

        for (Attribute att : attributes) {
            sb.append(' ');
            sb.append(att.getFormattedContent());
        }

        if (elements.size() > 0) {
            sb.append(" >"); //$NON-NLS-1$
            for (Element element : elements) {
                OutputUtilities.newLine(sb);
                sb.append(element.getFormattedContent(indentLevel + 1));
            }
            OutputUtilities.newLine(sb);
            OutputUtilities.xmlIndent(sb, indentLevel);
            sb.append("</"); //$NON-NLS-1$
            sb.append(name);
            sb.append('>');

        } else {
            sb.append(" />"); //$NON-NLS-1$
        }

        return sb.toString();
    }

    public void setName(String name) {
        this.name = name;
    }
}
