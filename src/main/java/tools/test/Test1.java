package tools.test;

import tools.mygenerator.api.dom.java.FullyQualifiedJavaType;

/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月7日 下午3:14:52 
* @version 
*/
public class Test1 {
	public static void main(String[] args) {
		FullyQualifiedJavaType f=new FullyQualifiedJavaType("com.tools.Student");

		FullyQualifiedJavaType f1=FullyQualifiedJavaType.getDateInstance();
		System.out.println(f.getImportList());
	}

}
