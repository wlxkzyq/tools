package tools.excel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import tools.test.PremMonth;

/** 
* 导出excell文件工具类
* @author 作者 : zyq
* 创建时间：2016年12月10日 下午3:06:42 
* @version 
*/
public class ExportExcellUtil {
	
	public ExportExcellUtil(){
		this.workbook=new HSSFWorkbook();
	}
	public ExportExcellUtil(InputStream tempInputStream){
		try {
			this.workbook=WorkbookFactory.create(tempInputStream);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 通过模板来生产excell文件
	 * @param tempFile 模板文件
	 */
	public ExportExcellUtil(File tempFile){
		try {
			this.workbook=WorkbookFactory.create(tempFile);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 通过模板来生产excell文件
	 * @param tempFile	模板文件路径
	 */
	public ExportExcellUtil(String tempFile){
		try {
			this.workbook=WorkbookFactory.create(new File(tempFile));
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//默认行高(*N 代表n像素,非数据行的行高，默认的其它行的行高)
	private short lineHeight=(short)15.625*20;
	
	/**
	 * 要导出的数据列，导出的数据列按该数组排序
	 * 如果该字段为空，默认导出实体类被注解的所有字段
	 */
	private String[] exportColumns;
	
	private Workbook workbook;
	/**
	 * sheet名字
	 */
	private String sheetName;
	
	/**
	 * title样式
	 * 可以获取workbook自己设置样式，给样式赋值一个对象(必须获取该实体类的workbook)
	 */
	private CellStyle titleStyle;
	/**
	 * 正常数据样式
	 * 可以获取workbook自己设置样式，给样式赋值一个对象 (必须获取该实体类的workbook)
	 */
	private CellStyle normalStyle;
	
	
	/**
	 * 数据库字段与导出excel列中文名key,value键值对对照
	 */
	private Map<String,String> columnMap;
	
	private Map<String,Integer> columnWidthMap;
	
	
	public Workbook getWorkbook() {
		return workbook;
	}
	public void setLineHeight(short lineHeight) {
		this.lineHeight = lineHeight;
	}
	public void setTitleStyle(CellStyle titleStyle) {
		this.titleStyle = titleStyle;
	}
	public void setNormalStyle(CellStyle normalStyle) {
		this.normalStyle = normalStyle;
	}
	public void setColumnMap(Map<String, String> columnMap) {
		this.columnMap = columnMap;
	}
	public void setColumnWidthMap(Map<String, Integer> columnWidthMap) {
		this.columnWidthMap = columnWidthMap;
	}
	
	
	/**
	 * 设置title样式
	 */
	private void editTitleStyle(){
		Font titleFont= workbook.createFont();
		//设置标题字体
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);//粗体显示
		titleFont.setFontHeightInPoints((short) 12);  
		titleFont.setFontName("宋体");  
		titleFont.setColor(HSSFColor.BLACK.index); 
		String dateStyle="yyyy-mm-dd";
		DataFormat dataFormat = workbook.createDataFormat();
		titleStyle = workbook.createCellStyle();
		titleStyle.setFont(titleFont);//设置字体
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//设置水平对齐方式
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //设置垂直对齐方式
        titleStyle.setWrapText(false); // 是否换行
        titleStyle.setDataFormat(dataFormat.getFormat(dateStyle));//设置日期格式
        titleStyle.setBorderBottom((short) 1); //设置边框线
        titleStyle.setBorderLeft((short) 1);
        titleStyle.setBorderRight((short) 1);
        titleStyle.setBorderTop((short) 1);
        
	}
	
	/**
	 * 设置数据样式
	 */
	private void editNormalStyle(){
		Font normalFont=workbook.createFont();
		//设置内容字体
		normalFont.setFontHeightInPoints((short) 11);  
		normalFont.setFontName("宋体");  
		normalFont.setColor(HSSFColor.BLACK.index);
		//设置正常数据样式
		normalStyle =  workbook.createCellStyle();
		String dateStyle="yyyy-mm-dd";
		DataFormat dataFormat = workbook.createDataFormat();
		normalStyle.setFont(normalFont);
        normalStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        normalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 
        
        //normalStyle.setFillForegroundColor((short) 13);// 设置背景色
        //normalStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        normalStyle.setWrapText(false); // 是否换行
        normalStyle.setDataFormat(dataFormat.getFormat(dateStyle));
        normalStyle.setBorderBottom((short) 1);
        normalStyle.setBorderLeft((short) 1);
        normalStyle.setBorderRight((short) 1);
        normalStyle.setBorderTop((short) 1);
	}
	
	private void fillingSheet(List<?> list,Sheet sheet,int startRow) throws Exception{
		//sheet.setDefaultRowHeight(lineHeight);
		if(list==null||list.size()==0)return;
		Class cla=list.get(0).getClass();
		sheet.setDefaultRowHeight(lineHeight);
		List<Method> methods=sortMethod(cla);
		//填充title行
		Row titlerow=sheet.createRow(startRow);
		Cell titlecell;
		titlerow.setHeight((short)(15.625*25));
		if(titleStyle==null){editTitleStyle();}
		for (int i = 0; i < exportColumns.length; i++) {
			Integer width=null;
			if(columnWidthMap!=null){
				width=columnWidthMap.get(exportColumns[i]);
			}
			if(width==null||width==0){
				width=exportColumns[i].getBytes().length*256;
			}
			sheet.setColumnWidth(i, width);
			titlecell=titlerow.createCell(i);
			titlecell.setCellValue(exportColumns[i]);
			titlecell.setCellStyle(titleStyle);
		}
		//填充list中的数据
		Row normalrow;
		Cell normalcell;
		if(normalStyle==null){editNormalStyle();}
		StringBuilder cellValue=new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			normalrow=sheet.createRow(startRow+1+i);
			normalrow.setHeight(lineHeight);
			for (int j = 0; j < methods.size(); j++) {
				normalcell=normalrow.createCell(j);
				Object val=methods.get(j).invoke(list.get(i));
				//如果得到的值为空，若要对值进行格式化也可以在这操作
				if(val==null){
					cellValue=new StringBuilder("");
				}else{
					cellValue=new StringBuilder(val.toString());
					
				}
				//为sheet单元格设置值
				normalcell.setCellValue(cellValue.toString());
				normalcell.setCellStyle(normalStyle);
			}
		}
		
	}
	
	private void fillingSheetMap(List<Map<String,Object>> mapList,
			Sheet sheet,int startRow){
		if(mapList==null||mapList.size()==0)return;
		sheet.setDefaultRowHeight(lineHeight);
		//填充title行
		Row titlerow=sheet.createRow(startRow);
		Cell titlecell;
		titlerow.setHeight((short)(15.625*25));
		if(titleStyle==null){editTitleStyle();}
		for (int i = 0; i < exportColumns.length; i++) {
			Integer width=null;
			if(columnWidthMap!=null){
				width=columnWidthMap.get(exportColumns[i]);
			}
			if(width==null||width==0){
				width=exportColumns[i].getBytes().length*256;
			}
			sheet.setColumnWidth(i, width);
			titlecell=titlerow.createCell(i);
			titlecell.setCellValue(columnMap.get(exportColumns[i]));
			titlecell.setCellStyle(titleStyle);
		}
		//填充list中的数据
		Row normalrow;
		Cell normalcell;
		if(normalStyle==null){editNormalStyle();}
		StringBuilder cellValue=new StringBuilder();
		for (int i = 0; i < mapList.size(); i++) {
			normalrow=sheet.createRow(startRow+1+i);
			normalrow.setHeight(lineHeight);
			for (int j = 0; j < exportColumns.length; j++) {
				normalcell=normalrow.createCell(j);
				Object val=mapList.get(i).get(exportColumns[j]);
				//如果得到的值为空，若要对值进行格式化也可以在这操作
				if(val==null){
					cellValue=new StringBuilder("");
				}else{
					cellValue=new StringBuilder(val.toString());
					
				}
				//为sheet单元格设置值
				normalcell.setCellValue(cellValue.toString());
				normalcell.setCellStyle(normalStyle);
			}
		}
	}
	
	
	private List<Method> sortMethod(Class cla) throws NoSuchMethodException, SecurityException{
		List<Method> methodList=new ArrayList<Method>();
		Field[] fields=cla.getDeclaredFields();
		String fieldName="";
		StringBuilder fieldNameG=null;
		Method method=null;
		if(exportColumns==null||exportColumns.length==0){
			List<String> columns=new ArrayList<String>();
			for (int i = 0; i < fields.length; i++) {
				if(fields[i].getAnnotation(ExcelAnnotation.class)!=null){
					fieldName=fields[i].getName();
					fieldNameG=new StringBuilder(fieldName);
					//第一个字母变大写
					fieldNameG.setCharAt(0, (char)(fieldNameG.charAt(0)-(32)));
					fieldNameG.insert(0, "get");
					method=cla.getDeclaredMethod(fieldNameG.toString());
					methodList.add(method);
					columns.add(fields[i].getAnnotation(ExcelAnnotation.class).value());
				}
			}
			exportColumns=(String[]) columns.toArray(new String[0]);
		}else{
			for (int i = 0; i < exportColumns.length; i++) {
				for (int j = 0; j < fields.length; j++) {
					if(StringUtils.isNotBlank(exportColumns[i])&&fields[j].getAnnotation(ExcelAnnotation.class)!=null&&exportColumns[i].equals(fields[j].getAnnotation(ExcelAnnotation.class).value())){
						fieldName=fields[j].getName();
						fieldNameG=new StringBuilder(fieldName);
						//第一个字母变大写
						fieldNameG.setCharAt(0, (char)(fieldNameG.charAt(0)-(32)));
						fieldNameG.insert(0, "get");
						method=cla.getDeclaredMethod(fieldNameG.toString());
						methodList.add(method);
						break;
					}
				}
			}
		}
		
		return methodList;
	}
	/**
	 * list转excell
	 * 调用完方法记得os.flush();
	 * @param os  输出流
	 * @param list  list数据
	 * @param start	开始行(从0开始)
	 * @throws Exception
	 */
	public void listToExcell(OutputStream os,List<?> list,int start) throws Exception{
		if(StringUtils.isBlank(sheetName)){sheetName="Sheet1";}
		int n=workbook.getNumberOfSheets();
		Sheet sheet;
		if(n<1){
			sheet=workbook.createSheet(sheetName);
		}else{
			sheet=workbook.getSheetAt(0);
		}
		fillingSheet(list, sheet, start);
		workbook.write(os);
	}
	/**
	 * list转excell
	 * @param file 输出文件
	 * @param list	list数据
	 * @throws Exception
	 */
	public void listToExcell(File file,List<?> list) throws Exception{
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
		listToExcell(bos, list, 0);
		bos.flush();
		bos.close();
	}
	/**
	 * list转excell
	 * @param file	输出文件
	 * @param list	list数据
	 * @param start	开始行(从0开始)
	 * @throws Exception
	 */
	public void listToExcell(File file,List<?> list,int start) throws Exception{
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
		listToExcell(bos, list, start);
		bos.flush();
		bos.close();
	}
	public void listToExcell(HttpServletResponse response,List<?> list,int start,String fileName) throws Exception{
		OutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		fileName=URLEncoder.encode(fileName, "utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=\""
				+ fileName + ".xls\"");
		BufferedOutputStream bos=new BufferedOutputStream(os);
		listToExcell(bos, list, start);
		bos.flush();
		bos.close();
	}
	
	/**
	 * map类型list转excel
	 * @param outputStream	输出流
	 * @param mapList	待转换的list
	 * @param start		开始行
	 * @throws Exception 
	 */
	public void mapListToExcel(OutputStream os,
			List<Map<String,Object>> mapList,int start) throws Exception{
		if(StringUtils.isBlank(sheetName)){sheetName="Sheet1";}
		int n=workbook.getNumberOfSheets();
		Sheet sheet;
		if(n<1){
			sheet=workbook.createSheet(sheetName);
		}else{
			sheet=workbook.getSheetAt(0);
		}
		fillingSheetMap(mapList, sheet, start);
		workbook.write(os);
	}
	
	
	public static void main(String[] args) throws Exception {
		//System.out.println((Short.MAX_VALUE));
		File file=new File("D:\\workingSpace2\\app_Bs\\src\\main\\webapp\\supervise\\supervise.xls");
		ImportExcelUtil i=new ImportExcelUtil();
		Date dd=new Date();
		List<PremMonth> list= i.excelTOlist(file, PremMonth.class,1,1);
		System.out.println(new Date().getTime()-dd.getTime());
		System.out.println(list);
		
		
		ExportExcellUtil e=new ExportExcellUtil("D:\\ddd.xls");
		//ExportExcellUtil e=new ExportExcellUtil();
		e.setLineHeight((short)(15.625*20));
		//e.exportColumns=new String[]{"当月累计保费","排名"};
		e.listToExcell(new File("d:/lll.xls"), list,4);
		System.out.println(e.exportColumns);
	}

}
