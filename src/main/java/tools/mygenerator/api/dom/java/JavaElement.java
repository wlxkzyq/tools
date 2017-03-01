package tools.mygenerator.api.dom.java;

import java.util.ArrayList;
import java.util.List;

import tools.mygenerator.api.dom.OutputUtilities;


/** 
* java元素
* @author 作者 : zyq
* 创建时间：2017年3月1日 上午11:04:34 
* @version 
*/

public abstract class JavaElement {
	private List<String> javaDocLines;

	/**
	 * java范围修饰符
	 */
    private JavaVisibility visibility = JavaVisibility.DEFAULT;

    /**
     * 是否静态
     */
    private boolean isStatic;

    /**
     * 是否final
     */
    private boolean isFinal;

    private List<String> annotations;

    /**
     *  
     */
    public JavaElement() {
        super();
        javaDocLines = new ArrayList<String>();
        annotations = new ArrayList<String>();
    }
    
    /**
     * Copy Constructor.
     * 
     * @param original
     */
    public JavaElement(JavaElement original) {
        this();
        this.annotations.addAll(original.annotations);
        this.isFinal = original.isFinal;
        this.isStatic = original.isFinal;
        this.javaDocLines.addAll(original.javaDocLines);
        this.visibility = original.visibility;
    }

    /**
     * @return Returns the javaDocLines.
     */
    public List<String> getJavaDocLines() {
        return javaDocLines;
    }

    public void addJavaDocLine(String javaDocLine) {
        javaDocLines.add(javaDocLine);
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public void addAnnotation(String annotation) {
        annotations.add(annotation);
    }

    /**
     * @return Returns the visibility.
     */
    public JavaVisibility getVisibility() {
        return visibility;
    }

    /**
     * @param visibility
     *            The visibility to set.
     */
    public void setVisibility(JavaVisibility visibility) {
        this.visibility = visibility;
    }

    public void addSuppressTypeWarningsAnnotation() {
        addAnnotation("@SuppressWarnings(\"unchecked\")"); //$NON-NLS-1$
    }

    public void addFormattedJavadoc(StringBuilder sb, int indentLevel) {
        for (String javaDocLine : javaDocLines) {
            OutputUtilities.javaIndent(sb, indentLevel);
            sb.append(javaDocLine);
            OutputUtilities.newLine(sb);
        }
    }

    public void addFormattedAnnotations(StringBuilder sb, int indentLevel) {
        for (String annotation : annotations) {
            OutputUtilities.javaIndent(sb, indentLevel);
            sb.append(annotation);
            OutputUtilities.newLine(sb);
        }
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }
} 
