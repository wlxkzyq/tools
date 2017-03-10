package tools.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/** 
* 文件操作工具类
* @author 作者 : zyq
* 创建时间：2017年3月9日 下午8:16:06 
* @version 
*/
public class FileUtil {
	
	/**
	 * 将字符串写入到文件
	 * @param file	要写入的文件
	 * @param content	字符串
	 * @param fileEncoding	文件编码
	 */
	public static void writeFile(File file,String content,String fileEncoding){
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file, false);
			OutputStreamWriter osw;
	        if (fileEncoding == null) {
	            osw = new OutputStreamWriter(fos);
	        } else {
	            osw = new OutputStreamWriter(fos, fileEncoding);
	        }
	        BufferedWriter bw = new BufferedWriter(osw);
	        bw.write(content);
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("写入文件失败！！");
		}
        
	}
	
	public static File getUniqueFileName(File directory, String fileName){
		File answer = null;

        // try up to 1000 times to generate a unique file name
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 1000; i++) {
            sb.setLength(0);
            sb.append(fileName);
            sb.append('.');
            sb.append(i);

            File testFile = new File(directory, sb.toString());
            if (!testFile.exists()) {
                answer = testFile;
                break;
            }
        }

        if (answer == null) {
            throw new RuntimeException("获取唯一文件名失败,【"+directory.getAbsolutePath()+"】目录下"); //$NON-NLS-1$
        }

        return answer;
	}
	
    public static File getDirectory(String targetProject, String targetPackage) throws Exception
             {
        // targetProject is interpreted as a directory that must exist
        //
        // targetPackage is interpreted as a sub directory, but in package
        // format (with dots instead of slashes). The sub directory will be
        // created
        // if it does not already exist

        File project = new File(targetProject);
        if (!project.isDirectory()) {
            throw new Exception(targetProject+" 目录不存在！");
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(targetPackage, "."); //$NON-NLS-1$
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
            sb.append(File.separatorChar);
        }

        File directory = new File(project, sb.toString());
        if (!directory.isDirectory()) {
            boolean rc = directory.mkdirs();
            if (!rc) {
                throw new Exception(directory.getAbsolutePath()+"目录创建失败！");
            }
        }

        return directory;
    }
	
	public static void main(String[] args) {
		File f=new File("d:");
		;
		System.out.println(FileUtil.getUniqueFileName(f, "ddd"));
	}

}
