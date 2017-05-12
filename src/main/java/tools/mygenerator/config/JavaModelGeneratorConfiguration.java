package tools.mygenerator.config;

import static tools.mygenerator.internal.util.StringUtility.stringHasValue;
import static tools.mygenerator.internal.util.messages.Messages.getString;

import java.util.List;

import tools.mygenerator.api.dom.xml.Attribute;
import tools.mygenerator.api.dom.xml.XmlElement;

/** 
* java实体类生成器配置类
* @author 作者 : zyq
* 创建时间：2017年3月7日 下午4:51:54 
* @version 
*/
public class JavaModelGeneratorConfiguration  extends PropertyHolder {

    private String targetPackage;

    private String targetProject;
    
    /**
     * 类名前缀
     */
    private String classNamePrefix; 

    /**
     * 类名后缀
     */
    private String classNameSuffix;
    
    
    /**
     * 
     */
    public JavaModelGeneratorConfiguration() {
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

    
    public String getClassNamePrefix() {
		return classNamePrefix;
	}

	public void setClassNamePrefix(String classNamePrefix) {
		this.classNamePrefix = classNamePrefix;
	}

	public String getClassNameSuffi() {
		return classNameSuffix;
	}

	public void setClassNameSuffix(String classNameSuffix) {
		this.classNameSuffix = classNameSuffix;
	}


	public XmlElement toXmlElement() {
        XmlElement answer = new XmlElement("javaModelGenerator"); //$NON-NLS-1$

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
            errors.add(getString("ValidationError.0", contextId)); //$NON-NLS-1$
        }

        if (!stringHasValue(targetPackage)) {
            errors.add(getString("ValidationError.12", //$NON-NLS-1$
                    "JavaModelGenerator", contextId)); //$NON-NLS-1$
        }
    }
}
