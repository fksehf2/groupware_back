package frame.futil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * <pre>
 * 1. 클래스명 : ExcelUtil.java
 * 2. 작성일 : 2021. 5. 12.
 * 3. 작성자 : jij
 * 4. 설명 : @!@ 엑셀 업로드 유틸
 * </pre>
 */
public class ExcelUtil {

	/**

	 * 엑셀 파일 읽기

	 * @param file

	 * @param sheetNum

	 * @param strartRowNum

	 * @param startCelNum

	 * @return List<HashMap<Integer, String>>

	 * @throws Exception

	 */

	public List<HashMap<Integer, String>> excelReadSetValue(CommonsMultipartFile file, int sheetNum, int strartRowNum, int startCelNum) throws Exception {

		List<HashMap<Integer, String>> resultList = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		

		//xls, xlsx 구분

		Workbook workbook = null;

		if(file.getOriginalFilename().toUpperCase().endsWith("XLSX")) {

			workbook = new XSSFWorkbook(file.getInputStream());

		}

		else {

			workbook = new HSSFWorkbook(file.getInputStream());

		}

		

		//Sheet 수 확인

		int sheetCnt = workbook.getNumberOfSheets();

		int listNum = 0;



		try {

			if (sheetCnt > 0) {

				//첫번째 Sheet 선택

				Sheet sheet = workbook.getSheetAt(sheetNum);

				

				//Sheet의 Row와 Cell 수 확인

				int rows = sheet.getPhysicalNumberOfRows();

				int cells = sheet.getRow(0).getPhysicalNumberOfCells();

				

				HashMap<Integer, String> valueMap = null;

	

				//Header Row 빼고 시작(0에서 시작)

				for(int r = strartRowNum ; r < rows; r++) {

					//String device_id = "";

					valueMap = new HashMap<Integer, String>();

	

					//한 줄씩 읽고 데이터 저장

					Row row = sheet.getRow(r);

					if (row != null) {

						//Cell 기본값 빼고 시작(0에서 시작)

						for(int c = startCelNum ; c < cells ; c++) {

							Cell cell = row.getCell(c);

							

							if (cell != null) {

								String value = "";

								

								switch(cell.getCellType()) {

								case Cell.CELL_TYPE_BLANK :

									value = "";

									break;

								case Cell.CELL_TYPE_BOOLEAN :

									value = "" + cell.getBooleanCellValue();

									break;

								case Cell.CELL_TYPE_ERROR :

									value = "" + cell.getErrorCellValue();

									break;

								case Cell.CELL_TYPE_FORMULA :

									value = cell.getCellFormula();

									break;

								case Cell.CELL_TYPE_NUMERIC :

									if(HSSFDateUtil.isInternalDateFormat(cell.getCellStyle().getDataFormat())) {

										value = sdf.format(cell.getDateCellValue());

									}

									else {

										cell.setCellType(Cell.CELL_TYPE_STRING ); 

										value = cell.getStringCellValue(); 

									}

									break;

								case Cell.CELL_TYPE_STRING :

									value = cell.getStringCellValue();

									break;

								}

								

								//공백과 트림 제거

								value = value.trim().replaceAll(" ", "");

								

								valueMap.put(c, value);

							}else {
								valueMap.put(c, null);
							}

						}//end col for

						resultList.add(listNum++, valueMap);

					}//end if

				}//end row for

			}

		} catch(Exception e) {

			e.getStackTrace();

		}

		return resultList;

	}

	

}