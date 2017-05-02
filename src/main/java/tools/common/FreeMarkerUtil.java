package tools.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import tools.pub.PathUtil;
import tools.test.PremMonth;

/** 
* FreeMarker工具类 
* @author 作者 : zyq
* 创建时间：2017年1月16日 下午6:20:17 
* @version 
*/
public class FreeMarkerUtil {
	
	private static Configuration cfg;
	
	/**
	 * 打印到控制台
	 * @param ftlName
	 * @param root
	 * @param ftlPath
	 */
	public static void print(String ftlName, Map<String,Object> root, String ftlPath){
		try {
			Template temp = getTemplate(ftlName, ftlPath);		//通过Template可以将模板文件输出到相应的流
			temp.process(root, new PrintWriter(System.out));
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出到输出到文件
	 * @param ftlName   ftl文件名
	 * @param root		传入的map
	 * @param outFile	输出后的文件全部路径
	 * @param filePath	输出前的文件上部路径
	 */
	public static void printFile(String ftlName, Map<String,Object> root, String outFile, String filePath, String ftlPath) throws Exception{
		try {
			File file = new File(PathUtil.getClassPath() + filePath + outFile);
			if(!file.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
				file.getParentFile().mkdirs();				//不存在就全部创建
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			Template template = getTemplate(ftlName, ftlPath);
			template.process(root, out);					//模版输出
			out.flush();
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取字符串形式
	 * @param ftlName
	 * @param root
	 * @param ftlPath
	 * @return
	 */
	public static String getString(String ftlName, Map<String,Object> root, String ftlPath){
		try {
			Template temp = getTemplate(ftlName, ftlPath);		//通过Template可以将模板文件输出到相应的流
			StringWriter sw=new StringWriter();
			BufferedWriter writer = new BufferedWriter(sw);
			temp.process(root, writer);
			writer.flush();
			writer.close();
			System.out.println(sw.toString());
			return sw.toString();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 加载模板
	 * @param ftlName
	 * @param ftlPath
	 * @return
	 */
	public static Template getTemplate(String ftlName, String ftlPath){
		if(cfg==null){
			cfg=new Configuration();
			cfg.setEncoding(Locale.CHINA, "utf-8");
		}
		try {
			cfg.setDirectoryForTemplateLoading(new File(PathUtil.getClassPath()+ftlPath));	
			Template temp = cfg.getTemplate(ftlName);
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Map<String,Object> root=new HashMap<String,Object>();
		root.put("user", "张勇强");
		List<PremMonth> list=new ArrayList<PremMonth>();
		PremMonth p=new PremMonth();
		p.setBranchName("分支");
		list.add(p);
		PremMonth p2=new PremMonth();
		p2.setBranchName("分支2");
		list.add(p2);
		root.put("animals", list);
		FreeMarkerUtil.getString("tt.ftl", root, "");
	}

}
