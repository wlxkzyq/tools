package tools.common.qrcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * 二维码工具类<br>
 * maven依赖<br>
 * <groupId>com.google.zxing</groupId>
	    <artifactId>core</artifactId>
	    <version>3.2.0</version>
	</dependency>
 * 
 * @author 作者 : zyq 创建时间：2016年10月12日 下午5:40:35
 * @version 0.0.1SNAPSHOT
 */
public class QrcodeUtil {
	
	/**
	 * 字符串==》二维码图片
	 * @param content  要转换的字符串
	 * @param stream	二维码图片的输出流
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void getImage(String content, OutputStream stream) throws Exception {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();// Zxing是Google提供的关于条码
		@SuppressWarnings("rawtypes")
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);// 这里是照片的大小
		MatrixToImageWriter.writeToStream(bitMatrix, "jpg", stream);
	}
	/**
	 * 二维码图片==》字符串
	 * @param inputStream 二维码图片输入流
	 * @return	字符串
	 */
	public String getValue(InputStream inputStream){
		BufferedImage image=null;
		try {
			image = ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		LuminanceSource source = new BufferedImageLuminanceSource(image); 
		Binarizer binarizer = new HybridBinarizer(source); 
		BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer); 
		Map hints = new HashMap(); 
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); 
		Result result=null;
		try {
			result = new MultiFormatReader().decode(binaryBitmap,hints);
		} catch (NotFoundException e) {
			e.printStackTrace();
		} 
		System.out.println("result = "+ result.toString()); 
		return "";
	}
	
}
