package tools.pub;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** 
* 加密方法类
* @author 作者 : zyq
* 创建时间：2016年12月27日 上午11:04:08 
* @version 
*/
public class Safe {
	/**
	 * DSA, RSA, MD5 or SHA-1
	 * @param s
	 * @param signType
	 * @return
	 */
	public static String md5(String s,String signType){
		String md5String="";
		if(signType==null||signType.length()<1){signType="MD5";}
		try {
			MessageDigest md=MessageDigest.getInstance(signType);
			md.update(s.getBytes("utf-8"));
			byte[] resultByteArray = md.digest();
			md5String=byteArrayToHex(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return md5String;
	}
	private static String byteArrayToHex(byte[] byteArray) {
		// 首先初始化一个字符数组，用来存放每个16进制字符  
		  char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
		  // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		  char[] resultCharArray =new char[byteArray.length * 2];
		  // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		  int index = 0;
		  for (byte b : byteArray) {
		     resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
		     resultCharArray[index++] = hexDigits[b& 0xf];
		  }
		  // 字符数组组合成字符串返回
		  return new String(resultCharArray);
	}
	
	public static void main(String[] args) {
		System.out.println(Safe.md5("admin1", "SHA-1").toLowerCase());
		//ee799c79f95442fe304a9a8f62d3988ae034cde8
		//6c7ca345f63f835cb353ff15bd6c5e052ec08e7a
	}

}

