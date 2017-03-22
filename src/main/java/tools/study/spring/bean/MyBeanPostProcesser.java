package tools.study.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月16日 下午7:52:57 
* @version 
*/
public class MyBeanPostProcesser implements BeanPostProcessor{
	
	public MyBeanPostProcesser(){
		super();
		System.out.println("这是BeanPostProcessor实现类构造器！！");
	}
	

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！");
		return bean;
	}

}
