package tools.common;

import java.util.StringTokenizer;

/** 
* 字符串操作工具类
* @author 作者 : zyq
* 创建时间：2017年3月27日 下午5:55:35 
* @version 
*/
public class StringUtils {
	
	private StringUtils(){
		throw new IllegalAccessError("不允许创建工具类对象！");
	}
	
	/**
     * <p>判断字符串 CharSequence is empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} 如果 CharSequence 是 "" 或者 null
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
	
	/**
	 * 获取中文形式的变量,在日志信息中容易区分变量
	 * <pre>
	 * String==> 【String】
	 * <pre>
	 * @param v	待格式化参数
	 * @return
	 */
	public static String formatVariable(String v){
		return "【"+v+"】";
	}
	
	
	
	/**
	 * <p>对于字符串<code>\"temp\"</code>如果直接打印会得到"temp"
	 * <p>该方法将获得原样形式的字符串 \"temp\"
	 * @param s
	 * @return
	 */
	public static String escapeStringForJava(String s) {
        StringTokenizer st = new StringTokenizer(s, "\"", true); //$NON-NLS-1$
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if ("\"".equals(token)) { //$NON-NLS-1$
                sb.append("\\\""); //$NON-NLS-1$
            } else {
                sb.append(token);
            }
        }

        return sb.toString();
    }
	
	public static void main(String[] args) {
		System.out.println(escapeStringForJava("\"fadf\""));
	}

}
