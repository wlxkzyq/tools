package tools.excel;
/** 
 * 注解字段，用来 标识 该字段对应的excel标题行的名字
 * @author 作者 : zyq
 * 创建时间：2016年5月12日 下午2:17:03 
 * @version 
 */
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnnotation {
	String value();
}
