package tools.study.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/** 
* spring bean 生命周期学习
* @author 作者 : zyq
* 创建时间：2017年3月16日 下午7:05:15 
* @version 
*/
public class Person implements BeanFactoryAware,BeanNameAware,InitializingBean,DisposableBean {
	
	private String name;
	
	private String address;
	
	private int phone;
	
	private BeanFactory beanFactory;
	
	public Person(){
		System.out.println("====调用构造方法！");
	}

	public String getName() {
		System.out.println("====调用getName方法");
		return name;
	}

	public void setName(String name) {
		System.out.println("====调用setName方法");
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
		this.beanFactory = beanFactory;
		
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("【BeanNameAware接口】调用BeanNameAware.setBeanName()");
		System.out.println(name);
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
		
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("【DiposibleBean接口】调用DiposibleBean.destory()");
		
	}

}
