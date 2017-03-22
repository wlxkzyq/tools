package tools.study.spring.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/** 
* spring Bean 生命周期学习
* @author 作者 : zyq
* 创建时间：2017年3月16日 下午5:27:34 
* @version 
*/
public class LifeCircleStudy {
	
	public static void main(String[] args) {
		System.out.println("现在开始初始化容器");
		//ApplicationContext factory=new FileSystemXmlApplicationContext("D:\\workingSpace2\\tools\\src\\main\\resources\\spring\\ApplicationContext.xml");
		ApplicationContext factory = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
		System.out.println("容器初始化成功");    
		//得到Preson，并使用
		Person person = factory.getBean("zhangsan",Person.class);
//		System.out.println(person);
		System.out.println("现在开始关闭容器！");
		((ClassPathXmlApplicationContext)factory).registerShutdownHook();
	}

}
