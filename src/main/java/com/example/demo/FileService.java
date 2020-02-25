package com.example.demo;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//import com.microsoft.schemas.office.visio.x2012.main.CellType;
//import com.readexcel.sample.SheetDetails;

@Service
public class FileService {

	
	
	@Autowired
	StudentRepository stuRep;
	
	
	public String saveFile(MultipartFile file) {
		
		Iterable<Student> stuList = null;
		String errorString = "";
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
//		String fullQuery = "";
		
		try {	

			InputStream fileSource = file.getInputStream();
			XSSFWorkbook workbook = new XSSFWorkbook(fileSource); // Access the workbook

			Sheet sheet = workbook.getSheetAt(0);
			// Create a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();


//				ArrayList<SheetDetails> details = new ArrayList<SheetDetails>();
			ArrayList<Student> studentsList = new ArrayList<Student>();			
			
			
//			String arrayQuery = "";
//			
			boolean errorFlag = false;
			
			Set<String> mySet = new HashSet<String>();
			
			
			for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
				
				Student stu = new Student();
				
				Row row = sheet.getRow(i);
				String singleInsert = "";
//						SheetDetails d = new SheetDetails();
				
//----------------------------------------------------------------------------------------------------------------------------

				Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				CellType celltype = cell.getCellType();
				if(celltype.equals(CellType.NUMERIC)) {
					singleInsert+=(int)cell.getNumericCellValue() + ",";
					
				} else {
					errorString += "\n row " + i + " cellno 1 must be numeric" ;
				}
//				

				cell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				celltype = cell.getCellType();
				if(celltype.equals(CellType.STRING)) {
//					singleInsert+= "'" + cell.getStringCellValue() + "',";
					String name = cell.getStringCellValue();
					
					if(mySet.contains(name)) {
						errorString += "duplicate name " + name + " for row " + i;
						errorFlag = true;
					} else {
						mySet.add(name);
					}
					
					stu.setName(cell.getStringCellValue());
				} else {
					errorString += "\n row " + i + " cellno 2 must be String" ;
				}
				
				cell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				celltype = cell.getCellType();
				if(celltype.equals(CellType.STRING)) {
					singleInsert+= "'" + cell.getStringCellValue() + "',";
					stu.setEmail(cell.getStringCellValue());
				} else {
					errorString += "\n row " + i + " cellno 3 must be String" ;
				}
				
				
				cell = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				celltype = cell.getCellType();
				if(celltype.equals(CellType.NUMERIC)) {
					singleInsert+=(int)cell.getNumericCellValue() + ",";
					stu.setEnglishscore((int)cell.getNumericCellValue());
				} else {
					errorString += "\n row " + i + " cellno 4 must be numeric" ;
				}
				
				cell = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				celltype = cell.getCellType();
				if(celltype.equals(CellType.NUMERIC)) {
					singleInsert+=(int)cell.getNumericCellValue() + ",";
					double cellValueDouble = cell.getNumericCellValue();
					stu.setMathscore((int) cellValueDouble);
				} else {
					errorString += "\n row " + i + " cellno 5 must be numeric" ;
				}

				
				studentsList.add(stu);
//				stuRep.save(stu);
//				
//				singleInsert = singleInsert.substring(0, singleInsert.length()-1);
//				singleInsert = "(" + singleInsert + "),";
//				arrayQuery += singleInsert;
			}
//			
//			arrayQuery = arrayQuery.substring(0, arrayQuery.length()-1);
//			fullQuery = "insert into students2 values " + arrayQuery + ";";
			
			
			
			
			
//			fr.saveAll(details);
			if(errorFlag!=true) {
				stuList = stuRep.saveAll(studentsList);
			}
			
			
			
			System.out.println("Data Entered successfully...");
			workbook.close();
			
			
		} catch (

		Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();

		}

//		
//		 try
//		    {
//		      // create a mysql database connection
//		      String myDriver = "com.mysql.jdbc.Driver";
//		      String myUrl = "jdbc:mysql://localhost/usermanagement";
//		      Class.forName(myDriver);
//		      Connection conn = DriverManager.getConnection(myUrl, "root", "randompass");
//		      
//		      Statement st = conn.createStatement();
//
//		      // note that i'm leaving "date_created" out of this insert statement
//		      st.executeUpdate(fullQuery);
//
//		      conn.close();
//		    }
//		    catch (Exception e)
//		    {
//		      System.err.println("Got an exception!");
//		      System.err.println(e.getMessage());
//		    }

		  
		
		return errorString;
	}
	
	
	
	public static String saveFileToTemp() {
		return "";
	}
	
	public static String getMissingRowsTemp() {
		return "";
	}
}