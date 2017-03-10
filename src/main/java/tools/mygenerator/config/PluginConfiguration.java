package tools.mygenerator.config;

import static tools.mygenerator.internal.util.StringUtility.stringHasValue;
import static tools.mygenerator.internal.util.messages.Messages.getString;

import java.util.List;

import tools.mygenerator.api.dom.xml.Attribute;
import tools.mygenerator.api.dom.xml.XmlElement;

/** 
* 插件配置类
* @author 作者 : zyq
* 创建时间：2017年3月10日 上午10:56:30 
* @version 
*/
public class PluginConfiguration extends TypedPropertyHolder{
    public PluginConfiguration() {
    }

    public XmlElement toXmlElement() {
        XmlElement answer = new XmlElement("plugin"); //$NON-NLS-1$
        if (getConfigurationType() != null) {
            answer.addAttribute(new Attribute("type", getConfigurationType())); //$NON-NLS-1$
        }

        addPropertyXmlElements(answer);

        return answer;
    }

    public void validate(List<String> errors) {
        if (!stringHasValue(getConfigurationType())) {
            errors.add(getString("ValidationError.17"));
        }
    }
}
