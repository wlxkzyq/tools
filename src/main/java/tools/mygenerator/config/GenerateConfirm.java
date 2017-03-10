package tools.mygenerator.config;

import java.util.ArrayList;
import java.util.List;

import tools.mygenerator.api.GeneratedJavaFile;
import tools.mygenerator.api.GeneratedXmlFile;
import tools.mygenerator.api.IntrospectedTable;
import tools.mygenerator.api.dom.java.CompilationUnit;
import tools.mygenerator.codegen.JavaEntityGenerator;

/** 
* 控制使用那些生成器
* @author 作者 : zyq
* 创建时间：2017年3月10日 上午11:42:17 
* @version 
*/
public class GenerateConfirm {
	
	public static List<GeneratedJavaFile> generateJavaFiles(List<String> generators,
			Context context,IntrospectedTable table,List<String> warnings){
		
		List<GeneratedJavaFile> generatedJavaFiles=new ArrayList<GeneratedJavaFile>();
		
		if(generators.contains(GeneratorRegistry.javaEntity)){
			JavaEntityGenerator jg=new JavaEntityGenerator();
			jg.setContext(context);
			jg.setIntrospectedTable(table);
			jg.setWarnings(warnings);
			List<CompilationUnit> compilationUnits=jg.getCompilationUnits();
			for (int j = 0; j < compilationUnits.size(); j++) {
				generatedJavaFiles.add(new GeneratedJavaFile(compilationUnits.get(j),
						context.getJavaModelGeneratorConfiguration().getTargetProject(), 
						context.getJavaFormatter()));
				
			}
		}

		return generatedJavaFiles;
	}
	
	public static List<GeneratedXmlFile> generateXmlFiles(List<String> generators,Context context,
			IntrospectedTable table,List<String> warnings){
		List<GeneratedXmlFile> generatedXmlFiles=new ArrayList<GeneratedXmlFile>();
//		for (int i = 0; i < xmlGenerators.length; i++) {
//			AbstractXmlGenerator xmlGenerator=GeneratorMap.xmlGeneratorMap.get(xmlGenerators[i]);
//			if(xmlGenerator==null){
//				throw new RuntimeException("["+xmlGenerators[i]+"]xml生成器不存在");
//			}else{
//				xmlGenerator.setContext(context);
//				xmlGenerator.setIntrospectedTable(table);
//				xmlGenerator.setWarnings(warnings);
//				
//				new GeneratedXmlFile(xmlGenerator.getDocument(),
//						fileName, targetPackage, targetProject, true, context.getXmlFormatter());
//			}
//		}
		
		return generatedXmlFiles;
		
	}

}
