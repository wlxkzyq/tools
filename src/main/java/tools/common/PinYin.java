package tools.common;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/** 
* 汉语拼音操作工具类
* maven依赖
* <groupId>com.belerweb</groupId>
	    <artifactId>pinyin4j</artifactId>
	    <version>2.5.0</version>
	</dependency>
* @author 作者 : zyq
* 创建时间：2016年11月29日 下午5:23:16 
* @version 
*/
public class PinYin {
	/**
	 * 把汉语变成拼音的形式 ，英文字符不变 
	 * 比如 '重量' ==> 'zhongliang'
	 * @param chinese 要转换的汉语
	 * @param firstSpell 是否每一个汉字转换成一个首字母
	 * @return 拼音 
	 */
	public static String toPinYin(String chinese,boolean firstSpell){
		char[] nameChar = chinese.toCharArray();  
		String pinyinName = "";  
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
        //设置生成拼音的大小写
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        //设置音调  WITHOUT_TONE：无音调  ；WITH_TONE_NUMBER：带音调
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        for (int i = 0; i < nameChar.length; i++) {  
            if (nameChar[i] > 128) {  
                try {  
                	if(!firstSpell){
                		pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
                	}else{
                		pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
                	}
                } catch (BadHanyuPinyinOutputFormatCombination e) {  
                    e.printStackTrace();  
                }  
            }else{  
                pinyinName += nameChar[i];  
            }  
        }  
        return pinyinName;
	}

}
