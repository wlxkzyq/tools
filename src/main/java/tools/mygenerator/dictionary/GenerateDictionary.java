package tools.mygenerator.dictionary;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import tools.mygenerator.api.IntrospectedColumn;
import tools.mygenerator.api.IntrospectedForeignKey;
import tools.mygenerator.api.IntrospectedPrimaryKey;
import tools.mygenerator.api.IntrospectedTable;

/** 
* 生成数据字典
* 
* 依赖poi相关jar包
* @author 作者 : zyq
* 创建时间：2016年12月24日 上午11:20:51 
* @version 
*/
public class GenerateDictionary {
	
	/**
	 * 通过模板来生产excell文件
	 * @param tempInputStream  模板文件输入流
	 */
	public GenerateDictionary(InputStream tempInputStream){
		try {
			this.workbook=WorkbookFactory.create(tempInputStream);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private Workbook workbook;
	
	private List<String> warns=new ArrayList<String>();
	
	private final Logger logger=Logger.getLogger(GenerateDictionary.class);
	
	/**
	 * 表名样式
	 */
	private CellStyle tableNameStyle;
	/**
	 * 主键样式
	 */
	private CellStyle pkStyle;
	/**
	 * 数据样式
	 */
	private CellStyle dataStyle;
	
	/**
	 * 设置表中文名样式
	 */
	private void editTableNameStyle(){
		Font tableNameFont= workbook.createFont();
		//表名样式
		tableNameFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		tableNameFont.setFontHeightInPoints((short) 12);  
		tableNameFont.setFontName("宋体");  
		tableNameFont.setColor(HSSFColor.BLACK.index); 
		String dateStyle="yyyy-mm-dd";
		DataFormat dataFormat = workbook.createDataFormat();
		tableNameStyle = workbook.createCellStyle();
		tableNameStyle.setFont(tableNameFont);//设置字体
		tableNameStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		tableNameStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);// 设置背景色
		tableNameStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置背景色(必须要这一行)
		
		tableNameStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//设置水平对齐方式
		tableNameStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //设置垂直对齐方式
		tableNameStyle.setWrapText(true); // 是否换行
		tableNameStyle.setDataFormat(dataFormat.getFormat(dateStyle));//设置日期格式
		tableNameStyle.setBorderBottom((short) 1); //设置边框线
		tableNameStyle.setBorderLeft((short) 1);
		tableNameStyle.setBorderRight((short) 1);
		tableNameStyle.setBorderTop((short) 1);
        
	}
	
	/**
	 * 设置主键样式
	 */
	private void editPkStyle(){
		Font pkFont= workbook.createFont();
		//主键样式
		pkFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		pkFont.setFontHeightInPoints((short) 12);  
		pkFont.setFontName("宋体");  
		pkFont.setColor(HSSFColor.BLACK.index); 
		String dateStyle="yyyy-mm-dd";
		DataFormat dataFormat = workbook.createDataFormat();
		pkStyle = workbook.createCellStyle();
		pkStyle.setFont(pkFont);//设置字体
		pkStyle.setFillForegroundColor(HSSFColor.YELLOW.index);// 设置背景色
		pkStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置背景色(必须要这一行)
		pkStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//设置水平对齐方式
		pkStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //设置垂直对齐方式
		pkStyle.setWrapText(true); // 是否换行
		pkStyle.setDataFormat(dataFormat.getFormat(dateStyle));//设置日期格式
		pkStyle.setBorderBottom((short) 1); //设置边框线
		pkStyle.setBorderLeft((short) 1);
		pkStyle.setBorderRight((short) 1);
		pkStyle.setBorderTop((short) 1);
        
	}
	/**
	 * 设置数据样式
	 */
	private void editDataStyle(){
		Font dataFont= workbook.createFont();
		//表名样式
		dataFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		dataFont.setFontHeightInPoints((short) 12);  
		dataFont.setFontName("宋体");  
		dataFont.setColor(HSSFColor.BLACK.index); 
		String dateStyle="yyyy-mm-dd";
		DataFormat dataFormat = workbook.createDataFormat();
		dataStyle = workbook.createCellStyle();
		dataStyle.setFont(dataFont);//设置字体
		
		//dataStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);// 设置背景色
		//dataStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置背景色(必须要这一行)
		
		dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//设置水平对齐方式
		dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //设置垂直对齐方式
		dataStyle.setWrapText(true); // 是否换行
		dataStyle.setDataFormat(dataFormat.getFormat(dateStyle));//设置日期格式
		dataStyle.setBorderBottom((short) 1); //设置边框线
		dataStyle.setBorderLeft((short) 1);
		dataStyle.setBorderRight((short) 1);
		dataStyle.setBorderTop((short) 1);
        
	}
	
	/**
	 * 获取模板得sheet并将第一行样式克隆给新的sheet返回
	 * @param sheetName
	 * @return
	 */
	private Sheet setFirstRow(String sheetName){
		Sheet oldSheet=workbook.getSheetAt(0);
		Row row=oldSheet.getRow(0);
		workbook.removeSheetAt(0);
		Sheet sheet=workbook.createSheet(sheetName);
		Row r=sheet.createRow(0);
		r.setRowStyle(row.getRowStyle());
		r.setHeightInPoints(row.getHeightInPoints());
		
		for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
			sheet.setColumnWidth(i, oldSheet.getColumnWidth(i));
			Cell c=r.createCell(i);
			c.setCellStyle(row.getCell(i).getCellStyle());
			c.setCellValue(row.getCell(i).getStringCellValue());
			
		}
		sheet.createFreezePane(0, 1, 13, 1);
		return sheet;
	}
	
	/**
	 * 设置合并单元格的样式
	 * @param cellRangeAddress
	 * @param sheet
	 */
	private void setCellRangeAddressStyle(CellRangeAddress cellRangeAddress,Sheet sheet,CellStyle style){
		Row row=null;
		Cell cell=null;
		for (int i = cellRangeAddress.getFirstRow(); i <= cellRangeAddress.getLastRow(); i++) {
			row=sheet.getRow(i);
			for (int j = cellRangeAddress.getFirstColumn(); j <= cellRangeAddress.getLastColumn(); j++) {
				cell=row.getCell(j);
				if(cell==null){
					cell=row.createCell(j);
				}
				cell.setCellStyle(style);
			}
			
		}
	}
	
	/**
	 * 设置合并单元格边框
	 * @param cra
	 * @param sheet
	 */
	private void setCellRangeAddressBorder(CellRangeAddress cra,Sheet sheet){
		RegionUtil.setBorderBottom(1, cra, sheet, workbook);
		RegionUtil.setBorderTop(1, cra, sheet, workbook);
		RegionUtil.setBorderLeft(1, cra, sheet, workbook);
		RegionUtil.setBorderRight(1, cra, sheet, workbook);
	}
	
	/**
	 * 生产数据字典
	 * @param os  输出流
	 * @param start  开始行
	 * @param tables  要生成数据字典的表
	 * @throws IOException 
	 */
	public void generateDictionary(OutputStream os,List<IntrospectedTable> tables,int start) throws IOException{
		if(tables==null||tables.size()<1){
			warns.add("传入的表为空或者数量小于1");
			return;
		}
		
		fillingSheet(tables, setFirstRow(tables.get(0).getTableCatalog()), start);
		workbook.write(os);
		
	}
	
	public void generateDictionary(String dictionPath,List<IntrospectedTable> tables) throws IOException{
		File file=new File(dictionPath);
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
		generateDictionary(bos, tables, 0);
		bos.flush();
		bos.close();
	}
	
	private void fillingSheet(List<IntrospectedTable> tables,Sheet sheet,int startRow){
		if(tableNameStyle==null){
			editTableNameStyle();
		}
		if(dataStyle==null){
			editDataStyle();
			
		}
		if(pkStyle==null){
			editPkStyle();
		}
		IntrospectedTable table=null;
		CellRangeAddress cra=null;
		int currentTableRow=startRow+1;
		Row row = null;
		Cell cell=null;
		int columnSize=0;
		List<IntrospectedColumn> columns=null;
		IntrospectedColumn column=null;
		
		Set<IntrospectedPrimaryKey> primaryKeys=null;
		Set<IntrospectedForeignKey> foreignKeys=null;
		for (int i = 0; i < tables.size(); i++) {
			table=tables.get(i);
			logger.debug("正在生成第   "+(i+1)+"   个表，共  "+tables.size()+"个表。" );
			primaryKeys=table.getPrimaryKeys();
			foreignKeys=table.getForeignKeys();
			columns=table.getColumns();
			if(columns!=null&&columns.size()>0){
				columnSize=columns.size();
			}else{
				columnSize=1;
			}
			cra=new CellRangeAddress(currentTableRow, currentTableRow+columnSize-1, 0, 0);
			sheet.addMergedRegion(cra);
			row = sheet.createRow(currentTableRow);
			cell=row.createCell(0);
			cell.setCellValue("数据流图");
			setCellRangeAddressBorder(cra,sheet);
			
			cra=new CellRangeAddress(currentTableRow, currentTableRow+columnSize-1, 1, 1);
			sheet.addMergedRegion(cra);
			cell=row.createCell(1);
			cell.setCellValue(table.getTableName());
			setCellRangeAddressBorder(cra,sheet);
			setCellRangeAddressStyle(cra, sheet, dataStyle);
			
			cra=new CellRangeAddress(currentTableRow, currentTableRow+columnSize-1, 2, 2);
			sheet.addMergedRegion(cra);
			cell=row.createCell(2);
			cell.setCellValue(table.getRemarks());
			
			setCellRangeAddressStyle(cra, sheet, tableNameStyle);
			setCellRangeAddressBorder(cra,sheet);
			
			//填充列数据
			String columnName="";
			
			Map<String,IntrospectedPrimaryKey> pkMap=new HashMap<String, IntrospectedPrimaryKey>();
			Map<String,IntrospectedForeignKey> fkMap=new HashMap<String, IntrospectedForeignKey>();
			IntrospectedPrimaryKey pk=null;
			IntrospectedForeignKey fk=null;
			Iterator<IntrospectedPrimaryKey> iterator=primaryKeys.iterator();
			while (iterator.hasNext()) {
				pk=iterator.next();
				pkMap.put(pk.getColumnName(), pk);
			}
			Iterator<IntrospectedForeignKey> fkiterator=foreignKeys.iterator();
			while (fkiterator.hasNext()) {
				fk=fkiterator.next();
				fkMap.put(fk.getFkColumnName(), fk);
			}
			
			for (int j = 0; j < columnSize; j++) {
				column=columns.get(j);
				row=sheet.getRow(currentTableRow+j);
				if(row==null){
					row=sheet.createRow(currentTableRow+j);
				}
				
				//判断是否主键
				boolean isPk=false;
				columnName=column.getColumnName();
				if(pkMap.containsKey(columnName)){isPk=true;}
				
				CellStyle nowStyle=null;
				if(isPk){
					nowStyle=pkStyle;
				}else{
					nowStyle=dataStyle;
				}
				cell=row.createCell(3);
				cell.setCellValue(column.getRemarks());
				cell.setCellStyle(nowStyle);
				
				cell=row.createCell(4);
				cell.setCellValue(column.getColumnName());
				cell.setCellStyle(nowStyle);
				
				cell=row.createCell(5);
				StringBuilder sb=new StringBuilder(column.getTypeName().toLowerCase());
				if(sb.toString().equals("double")||sb.toString().equals("float")){
					sb.append("(").append(column.getColumnSize()+","+column.getDecimalDigits()+")");
				}else if(sb.toString().equals("date")||sb.toString().equals("time")||sb.toString().equals("datetime")||sb.toString().equals("timestamp")){
				}else{
					sb.append("(").append(column.getColumnSize()+")");
				}
				cell.setCellValue(sb.toString());
				cell.setCellStyle(nowStyle);
				
				cell=row.createCell(6);
				cell.setCellValue(column.getNullable()==0?"flase":"true");
				cell.setCellStyle(nowStyle);
				
				cell=row.createCell(7);
				cell.setCellValue(column.getDefaultValue());
				cell.setCellStyle(nowStyle);
				
				cell=row.createCell(8);
				cell.setCellValue("");
				cell.setCellStyle(nowStyle);
				
				//主键
				cell=row.createCell(9);
				cell.setCellValue(isPk?"PK":"");
				cell.setCellStyle(nowStyle);
				
				//外键
				cell=row.createCell(10);
				fk=fkMap.get(columnName);
				cell.setCellValue(fk==null?"":fk.getPkTableName()+"."+fk.getPkColumnName());
				cell.setCellStyle(nowStyle);
				
				//备注
				cell=row.createCell(11);
				cell.setCellValue("");
				cell.setCellStyle(nowStyle);
				
				//建表语句
				cell=row.createCell(12);
				cell.setCellValue("");
				cell.setCellStyle(nowStyle);
			}
			currentTableRow+=columnSize+1;
		}
		
	}
	
	

}
