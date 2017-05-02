package tools.pub;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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
     * <p>Checks if any one of the CharSequences are empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isAnyEmpty(null)             = true
     * StringUtils.isAnyEmpty(null, "foo")      = true
     * StringUtils.isAnyEmpty("", "bar")        = true
     * StringUtils.isAnyEmpty("bob", "")        = true
     * StringUtils.isAnyEmpty("  bob  ", null)  = true
     * StringUtils.isAnyEmpty(" ", "bar")       = false
     * StringUtils.isAnyEmpty("foo", "bar")     = false
     * </pre>
     *
     * @param css  the CharSequences to check, may be null or empty
     * @return {@code true} if any of the CharSequences are empty or null
     * @since 3.2
     */
    public static boolean isAnyEmpty(CharSequence... css) {
      if (css==null ||css.length==0) {
        return true;
      }
      for (CharSequence cs : css){
        if (isEmpty(cs)) {
          return true;
        }
      }
      return false;
    }
    
    
    /**
     * <p>判断一个对象调用<code>toString()</code>方法后的字符串是否是 empty</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param object  待判断对象
     * @return {@code true} 对象是null或者 对象{@code toString()} 返回是 "" 或者 null
     */
    public static boolean isEmpty(final Object object) {
    	return object==null?true:isEmpty(object.toString());
    }
	
    /**
     * <p>检查一个对象数组是否包含某个为empty</p>
     * <p>Checks if any one of the CharSequences are empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isAnyEmpty(null)             = true
     * StringUtils.isAnyEmpty(null, "foo")      = true
     * StringUtils.isAnyEmpty("", "bar")        = true
     * StringUtils.isAnyEmpty("bob", "")        = true
     * StringUtils.isAnyEmpty("  bob  ", null)  = true
     * StringUtils.isAnyEmpty(" ", "bar")       = false
     * StringUtils.isAnyEmpty("foo", "bar")     = false
     * </pre>
     *
     * @param css  the CharSequences to check, may be null or empty
     * @return {@code true} if any of the CharSequences are empty or null
     * @since 3.2
     */
    public static boolean isAnyEmpty(Object... object) {
        if (object==null ||object.length==0) {
          return true;
        }
        for (Object cs : object){
          if (isEmpty(cs)) {
            return true;
          }
        }
        return false;
     }
    
    /**
     * <p>Checks if a CharSequence is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace
     * @since 2.0
     * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * <p>Checks if any one of the CharSequences are blank ("") or null and not whitespace only..</p>
     *
     * <pre>
     * StringUtils.isAnyBlank(null)             = true
     * StringUtils.isAnyBlank(null, "foo")      = true
     * StringUtils.isAnyBlank(null, null)       = true
     * StringUtils.isAnyBlank("", "bar")        = true
     * StringUtils.isAnyBlank("bob", "")        = true
     * StringUtils.isAnyBlank("  bob  ", null)  = true
     * StringUtils.isAnyBlank(" ", "bar")       = true
     * StringUtils.isAnyBlank("foo", "bar")     = false
     * </pre>
     *
     * @param css  the CharSequences to check, may be null or empty
     * @return {@code true} if any of the CharSequences are blank or null or whitespace only
     * @since 3.2
     */
    public static boolean isAnyBlank(CharSequence... css) {
      if (css==null ||css.length==0) {
        return true;
      }
      for (CharSequence cs : css){
        if (isBlank(cs)) {
          return true;
        }
      }
      return false;
    }
    
    /**
     * <p>Checks if a CharSequence is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace
     * @since 2.0
     * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
     */
    public static boolean isBlank(final Object object) {
    	return object==null?true:isBlank(object.toString());
    }
    
    /**
     * <p>Checks if any one of the CharSequences are blank ("") or null and not whitespace only..</p>
     *
     * <pre>
     * StringUtils.isAnyBlank(null)             = true
     * StringUtils.isAnyBlank(null, "foo")      = true
     * StringUtils.isAnyBlank(null, null)       = true
     * StringUtils.isAnyBlank("", "bar")        = true
     * StringUtils.isAnyBlank("bob", "")        = true
     * StringUtils.isAnyBlank("  bob  ", null)  = true
     * StringUtils.isAnyBlank(" ", "bar")       = true
     * StringUtils.isAnyBlank("foo", "bar")     = false
     * </pre>
     *
     * @param css  the CharSequences to check, may be null or empty
     * @return {@code true} if any of the CharSequences are blank or null or whitespace only
     * @since 3.2
     */
    public static boolean isAnyBlank(Object... object) {
      if (object==null ||object.length==0) {
        return true;
      }
      for (Object cs : object){
        if (isBlank(cs)) {
          return true;
        }
      }
      return false;
    }
    
    /**
     * <p>字符串trim()方法，处理了对象为null的情况</p>
     * <p>Removes control characters (char &lt;= 32) from both
     * ends of this String, handling {@code null} by returning
     * {@code null}.</p>
     *
     * <p>The String is trimmed using {@link String#trim()}.
     * Trim removes start and end characters &lt;= 32.
     * To strip whitespace use {@link #strip(String)}.</p>
     *
     * <p>To trim your choice of characters, use the
     * {@link #strip(String, String)} methods.</p>
     *
     * <pre>
     * StringUtils.trim(null)          = null
     * StringUtils.trim("")            = ""
     * StringUtils.trim("     ")       = ""
     * StringUtils.trim("abc")         = "abc"
     * StringUtils.trim("    abc    ") = "abc"
     * </pre>
     *
     * @param str  the String to be trimmed, may be null
     * @return the trimmed string, {@code null} if null String input
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }
    
    /**
     * 把字符串byte字节按照编码方式转换为字符串（如果编码为空，按照默认的编码方式）
     * 
     * Converts a <code>byte[]</code> to a String using the specified character encoding.
     * 
     * @param bytes
     *            the byte array to read from
     * @param charset
     *            the encoding to use, if null then use the platform default
     * @return a new String
     * @throws NullPointerException
     *             if {@code bytes} is null
     * @since 3.2
     * @since 3.3 No longer throws {@link UnsupportedEncodingException}.
     */
    public static String toEncodedString(byte[] bytes, Charset charset) {
        return new String(bytes, charset != null ? charset : Charset.defaultCharset());
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
		return new StringBuilder().append("【").append(v).append("】").toString();
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
		System.out.println(formatVariable("dfdaf"));
	}

}
