package com.htdk.utils.ftp;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	private static final String Date = null;

	@SuppressWarnings(value = { "deprecation", "unused" })
	public List<?> getValues(String filePath, boolean isMerge) {
		String values = null;
		int rowNum = 0;
		try {
			InputStream is = new FileInputStream(filePath);

			XSSFWorkbook xwb = new XSSFWorkbook(is);

			XSSFSheet sheet = xwb.getSheetAt(0);

			XSSFRow row;
			Cell cell;
			String val = null;

			List<List> outerList = new ArrayList<List>();
			FormulaEvaluator formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) xwb);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			if (isMerge) {
				rowNum = sheet.getFirstRowNum();
			} else {
				rowNum = sheet.getFirstRowNum() + 1;
			}

			// rowNum = sheet.getFirstRowNum() + 1;
			for (int i = rowNum; i < sheet.getPhysicalNumberOfRows(); i++) {
				List<String> lineList = new ArrayList<String>();
				row = sheet.getRow(i);
				short lastCellNum = row.getLastCellNum();
				int physicalNumberOfCells = row.getPhysicalNumberOfCells();
				for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {

					cell = row.getCell(j);
					if(null == cell) {
						val = "";
						lineList.add(val);
						continue;
					}
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						// case CellType.STRING.getCode():
						val = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						Boolean val1 = cell.getBooleanCellValue();
						val = val1.toString();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (XSSFDateUtil.isCellDateFormatted(cell)) {
							val = sdf.format(cell.getDateCellValue());
							// val = cell.toString();
						} else {
							if (isMerge == true) {
								val = String.valueOf(cell.getNumericCellValue());
							} else {
								DecimalFormat df = new DecimalFormat("0");
								val = df.format(cell.getNumericCellValue());
								val = subZeroAndDot(val);
							}
						}
						break;
					case Cell.CELL_TYPE_BLANK:
						val = "";
						break;
					case Cell.CELL_TYPE_FORMULA:
						if (XSSFDateUtil.isCellDateFormatted(cell)) {
							String stringValue = String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue());
							Calendar calendar = new GregorianCalendar(1900, 0, -1);
							Date d = calendar.getTime();
							int parseInt = Double.valueOf(stringValue).intValue();
							String plusDay = DateUtils.plusDay(parseInt, d);
							val = plusDay;
						} else {
							val = String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue());
						}
						break;
					}
					lineList.add(val);
//					System.out.print(val + "\t");
				}
				if (isMerge) {
					outerList.add(i, lineList);
				} else {
					outerList.add(i - 1, lineList);
				}
				// System.out.println("");
			}
			xwb.close();
			return outerList;
		} catch (Exception e) {
			System.out.println("error:" + e);
		}
		return null;

	}
	
	private String subZeroAndDot(String s) {
	    if (s.indexOf(".0") > 0) {
	        // 去掉多余的 
	        s = s.replaceAll("0+?$", ""); 
	       // 如果最后一位是.则去掉
	        s = s.replaceAll("[.]$", "");
	    } 
	   return s;
	}

	public static class XSSFDateUtil extends DateUtil {
		protected static int absoluteDay(Calendar cal, boolean use1904windowing) {
			return DateUtil.absoluteDay(cal, use1904windowing);
		}
	}

	public static void main(String args[]) {
		String filePath = "D:\\ALconBatchMasterData.xls";
		ReadExcel er = new ReadExcel();
		er.getValues(filePath, false);
	}

}
