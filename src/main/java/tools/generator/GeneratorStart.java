package tools.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import tools.common.PathUtil;

/** 
* generator 运行初始类
* @author 作者 : zyq
* 创建时间：2016年11月14日 上午9:45:46 
* @version 
*/
public class GeneratorStart {
	
	public static void main(String[] args) throws Exception{
	   List<String> warnings = new ArrayList<String>();
	   
	   /*如果指定了该参数，如果生成的java文件存在已经同名的文件，新生成的文件会覆盖原有的文件。
	     如果没有指定该参数，如果存在同名的文件，MBG会给新生成的代码文件生成一个唯一的名字(例如：
	    MyClass.java.1, MyClass.java.2 等等)。 重要: 生成器一定会自动合并或覆盖已经生成的XML文件。
	   */
	   boolean overwrite = true;
	   //指定配置文件的路径
	   File configFile = new File(PathUtil.getClassPath()+"generator/generatorConfig.xml");
	   ConfigurationParser cp = new ConfigurationParser(warnings);
	   Configuration config = cp.parseConfiguration(configFile);
	   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
	   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
	   myBatisGenerator.generate(null);
	   for (String string : warnings) {
		   System.err.println(string);
	   }
	}
	

}
