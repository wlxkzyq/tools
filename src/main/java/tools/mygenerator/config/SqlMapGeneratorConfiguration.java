package tools.mygenerator.config;

import static tools.mygenerator.internal.util.StringUtility.stringHasValue;
import static tools.mygenerator.internal.util.messages.Messages.getString;

import java.util.List;

import tools.mygenerator.api.dom.xml.Attribute;
import tools.mygenerator.api.dom.xml.XmlElement;


/** 
* sqlMapper生成配置
* @author 作者 : zyq
* 创建时间：2017年3月14日 下午7:30:01 
* @version 
*/
public class SqlMapGeneratorConfiguration extends PropertyHolder {
	private String targetPackage;

    private String targetProject;

    /**
	 *  
	 */
    public SqlMapGeneratorConfiguration() {
        super();
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public XmlElement toXmlElement() {
        XmlElement answer = new XmlElement("sqlMapGenerator"); //$NON-NLS-1$

        if (targetPackage != null) {
            answer.addAttribute(new Attribute("targetPackage", targetPackage)); //$NON-NLS-1$
        }

        if (targetProject != null) {
            answer.addAttribute(new Attribute("targetProject", targetProject)); //$NON-NLS-1$
        }

        addPropertyXmlElements(answer);

        return answer;
    }

    public void validate(List<String> errors, String contextId) {
        if (!stringHasValue(targetProject)) {
            errors.add(getString("ValidationError.1", contextId)); //$NON-NLS-1$
        }

        if (!stringHasValue(targetPackage)) {
            errors.add(getString("ValidationError.12", //$NON-NLS-1$
                    "SQLMapGenerator", contextId)); //$NON-NLS-1$
        }
    }

}
