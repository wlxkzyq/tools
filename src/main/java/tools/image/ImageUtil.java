package tools.image;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import net.coobird.thumbnailator.Thumbnails;

/** 
* 图片处理工具类
* 
* 需要依赖的jar包为<br>
* <textarea  rows=8 cols=40>
	d<dependency>
	    <groupId>net.coobird</groupId>
	    <artifactId>thumbnailator</artifactId>
	    <version>0.4.8</version>
	</dependency>
* </textarea>
* <p>
*  <a href="http://www.cnblogs.com/miskis/p/5500822.html">参考博客</a>
* @author 作者 : zyq
* 创建时间：2016年12月7日 上午10:43:07 
* @version 
*/
public class ImageUtil {
	public static void main(String[] args) throws IOException {
		Date d=new Date();
		FileInputStream fis=new FileInputStream("D:\\img\\1.jpg");
		FileOutputStream fos=new FileOutputStream("D:\\img\\ttt2.jpg");
		toSmallImage(fis, 50, 0, fos);
		System.out.println(new Date().getTime()-d.getTime());
	}
	
	/**
	 * 将图片按照宽度等比例压缩
	 * @param is   图片输入流
	 * @param width    压缩后图片宽度(单位：px)
	 * @param quelity   压缩后图片质量(从0.0 ~ 1.0 质量越来越高)
	 * @param os    压缩后图片的输出流
	 */
	public static void toSmallImage(InputStream is,int width,double quelity,OutputStream os){
		try {
			Thumbnails.of(is).width(width).outputQuality(quelity).toOutputStream(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
