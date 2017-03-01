package tools.excel;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import tools.test.PremMonth;

/** 
 * 该类读取一个excel文件，把数据转换成list形式
 * @author 作者 : zyq
 * 创建时间：2016年5月16日 下午1:41:32 
 * @version 
 */
public class ImportExcelUtil {
	
	/**
	 * excel文件转list，支持xls和xlsx格式
	 * <p>
	 * 注意：
	 * 	1、使用该方法需要搭配注解<code>ExcelAnnotation</code>
	 * 	2、实体类的成员变量的类型不要用int，double，用包装类 <code>Integer</code><code>Double</code>
	 * 	3、当然所有成员变量都使用String的会更好，对应的excel的每一列尽量都使用文本类型，设置方法为
	 * 		数据==》分列==》下一步==》下一步=》文本==》完成，这样数据不容易出错
	 * @param file 表格文件
	 * @param cla  目标list泛型类的class文件
	 * @param titleRow	标题行 行数(从1开始)
	 * @param sheetNum	第几个sheet(从1开始)
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List excelTOlist(File file,Class cla,int titleRow,int sheetNum){
		try {
			Workbook book=WorkbookFactory.create(file);
			Sheet sheet = book.getSheetAt(sheetNum-1);
			Row rowTitle=sheet.getRow(titleRow-1);
			List<String> tileList=new ArrayList<String>();
			
			List<Object> list=new ArrayList<Object>();
			String titleva="";
			for (int i = 0; i < rowTitle.getPhysicalNumberOfCells(); i++) {
				titleva=getCellFormatValue(rowTitle.getCell(i));
				if(titleva.length()>0){
					tileList.add(titleva);
				}else{
					break;
				}
				
			}
			String[] title=new String[tileList.size()];
			title= tileList.toArray(title);
			int rowcount = sheet.getPhysicalNumberOfRows();
			
			//Method[] methods=cla.getDeclaredMethods();
			//排序set方法
			List<Method> methodList=sortMethod(title, cla);
			int size;
			for (int i = titleRow; i <rowcount; i++) {
				size=0;
				Object object=cla.newInstance();
				for (int j = 0; j < title.length; j++) {
					String va[]=new String[]{getCellFormatValue(sheet.getRow(i).getCell(j))};
					methodInvoke(methodList.get(j),va, object);
					if(va[0].length()==0){size++;}
				}
				if(size>=title.length){break;}
				list.add(object);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("读取excel文件出错");
		} 
	}
	//该方法将excell的单元格值转化为字符串类型
		private static String getCellFormatValue(Cell cell){
			DateFormat df=DateFormat.getDateInstance();
			if(cell==null){return "";}
			int celltype = cell.getCellType();
			StringBuffer cellValue = new StringBuffer();
			switch (celltype) {
			case Cell.CELL_TYPE_STRING://文本
				cellValue = new StringBuffer(
						cell.getStringCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC://数字，日期
				if(DateUtil.isCellDateFormatted(cell)){
				cellValue = new StringBuffer(
						df.format(cell.getDateCellValue()));
				
				}else{
					cellValue=new StringBuffer(cell.getNumericCellValue()+"");
				}
				break;
			case Cell.CELL_TYPE_BLANK://空白
					cellValue=new StringBuffer("");
				break;
			case Cell.CELL_TYPE_BOOLEAN://布尔
				cellValue=new StringBuffer(String.valueOf(cell.getBooleanCellValue()));
			    break;
			case Cell.CELL_TYPE_ERROR://错误
				cellValue=new StringBuffer("");
			    break;
			case Cell.CELL_TYPE_FORMULA://公式
				cellValue=new StringBuffer("");
			    break;
			default:
				break;
			}
			return cellValue.toString().trim();
		}
		private static Date parseDate(String date) throws ParseException{
	    	Date dateValue=null;
	    	DateFormat df=null;
	    	if(date.length()<11){
	    		df=DateFormat.getDateInstance();
	    		dateValue=df.parse(date);
	    	}else{
	    		df=DateFormat.getDateTimeInstance();
	    		dateValue=df.parse(date);
	    	}
	    	return dateValue;
	    }
	    //把值转换成方法需要的类型然后执行反射方法
		private static void methodInvoke(Method method,String[] args,Object obj) throws Exception{
			Object[] arg=new Object[args.length];
			@SuppressWarnings("rawtypes")
			Class[] cla=method.getParameterTypes();
			for (int i = 0; i < cla.length; i++) {
				String paraName = cla[i].getSimpleName();
					if (paraName.equals("String")) {
						arg[i]=args[i];
					}
					if ((paraName.equals("int")||paraName.equals("Integer")) && !args[i].equals("")) {
						//arg[i]=Integer.parseInt(args[i]);
						arg[i]=(int)Double.parseDouble(args[i]);
					}
					if (paraName.equals("Date")) {
						arg[i]=parseDate(args[i]);
					}
					if ((paraName.equals("Double")||paraName.equals("double"))&&!args[i].equals("")) {
						arg[i]= Double.parseDouble(args[i]);
					}
			}
				method.invoke(obj, arg);
		}
		
		
		
		private static List<Method> sortMethod(String[] title,Class cla) throws NoSuchMethodException, SecurityException{
			List<Method> methodList=new ArrayList<Method>();
			Field[] fields=cla.getDeclaredFields();
			String fieldName="";
			StringBuilder fieldNameG=null;
			Method method=null;
			for (int i = 0; i < title.length; i++) {
				for (int j = 0; j < fields.length; j++) {
					if(StringUtils.isNotBlank(title[i])&&fields[j].getAnnotation(ExcelAnnotation.class)!=null&&title[i].equals(fields[j].getAnnotation(ExcelAnnotation.class).value())){
						fieldName=fields[j].getName();
						fieldNameG=new StringBuilder(fieldName);
						//第一个字母变大写
						fieldNameG.setCharAt(0, (char)(fieldNameG.charAt(0)-(32)));
						fieldNameG.insert(0, "set");
						method=cla.getDeclaredMethod(fieldNameG.toString(), fields[j].getType());
						methodList.add(method);
						break;
					}
				}
			}
			
			return methodList;
		}
		public static void main(String[] args) throws ParseException {
			File file=new File("D:\\workingSpace2\\app_Bs\\src\\main\\webapp\\supervise\\supervise.xls");
			ImportExcelUtil i=new ImportExcelUtil();
			Date dd=new Date();
			List<PremMonth> list= i.excelTOlist(file, PremMonth.class,1,1);
			System.out.println(new Date().getTime()-dd.getTime());
			System.out.println(list);
			Date d=i.parseDate("1990-09-09");
			System.out.println(d.toLocaleString());

		}

}
