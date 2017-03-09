package tools.mygenerator.api;

import tools.mygenerator.api.dom.xml.Document;

/** 
* 生成xml文件类
* @author 作者 : zyq
* 创建时间：2017年3月9日 下午9:02:51 
* @version 
*/
public class GeneratedXmlFile extends GeneratedFile {
    private Document document;

    private String fileName;

    private String targetPackage;

    private boolean isMergeable;
    
    private XmlFormatter xmlFormatter;

    /**
     * 
     * @param document
     * @param fileName
     * @param targetPackage
     * @param targetProject
     * @param isMergeable
     *            true if the file can be merged by the built in XML file
     *            merger.
     */
    public GeneratedXmlFile(Document document, String fileName,
            String targetPackage, String targetProject, boolean isMergeable,
            XmlFormatter xmlFormatter) {
        super(targetProject);
        this.document = document;
        this.fileName = fileName;
        this.targetPackage = targetPackage;
        this.isMergeable = isMergeable;
        this.xmlFormatter = xmlFormatter;
    }

    @Override
    public String getFormattedContent() {
        return xmlFormatter.getFormattedContent(document);
    }

    /**
     * @return Returns the fileName.
     */
    @Override
    public String getFileName() {
        return fileName;
    }

    /**
     * @return Returns the targetPackage.
     */
    @Override
    public String getTargetPackage() {
        return targetPackage;
    }

    @Override
    public boolean isMergeable() {
        return isMergeable;
    }
}
