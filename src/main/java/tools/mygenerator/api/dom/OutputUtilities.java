package tools.mygenerator.api.dom;

import java.util.Set;
import java.util.TreeSet;

import tools.mygenerator.api.dom.java.FullyQualifiedJavaType;

/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月1日 上午11:31:43 
* @version 
*/

public class OutputUtilities {
	private static final String lineSeparator;

    static {
        String ls = System.getProperty("line.separator"); //$NON-NLS-1$
        if (ls == null) {
            ls = "\n"; //$NON-NLS-1$
        }
        lineSeparator = ls;
    }

    /**
     * Utility class - no instances allowed
     */
    private OutputUtilities() {
        super();
    }

    /**
     * java代码控制缩进大小的方法
     * (four spaces per indent level).
     * 
     * @param sb
     *            a StringBuilder to append to
     * @param indentLevel
     *            the required indent level
     */
    public static void javaIndent(StringBuilder sb, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
            sb.append("    "); //$NON-NLS-1$
        }
    }

    /**
     * xml代码控制缩进大小的方法
     *  (two spaces per indent level).
     * 
     * @param sb
     *            a StringBuilder to append to
     * @param indentLevel
     *            the required indent level
     */
    public static void xmlIndent(StringBuilder sb, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
            sb.append("  "); //$NON-NLS-1$
        }
    }

    /**
     * 插入换行符
     * 
     * @param sb
     *            the StringBuilder to be appended to
     */
    public static void newLine(StringBuilder sb) {
        sb.append(lineSeparator);
    }

    /**
     * returns a unique set of "import xxx;" Strings for the set of types
     * 
     * @param importedTypes
     * @return
     */
    public static Set<String> calculateImports(
            Set<FullyQualifiedJavaType> importedTypes) {
        StringBuilder sb = new StringBuilder();
        Set<String> importStrings = new TreeSet<String>();
        for (FullyQualifiedJavaType fqjt : importedTypes) {
            for (String importString : fqjt.getImportList()) {
                sb.setLength(0);
                sb.append("import "); //$NON-NLS-1$
                sb.append(importString);
                sb.append(';');
                importStrings.add(sb.toString());
            }
        }

        return importStrings;
    }
    
    public static void main(String[] args) {
		StringBuilder sb=new StringBuilder("你好");
		OutputUtilities.newLine(sb);
		sb.append("fff");
		System.out.println(sb);
	}
} 
