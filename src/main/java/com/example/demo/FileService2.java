package com.example.demo;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//import com.microsoft.schemas.office.visio.x2012.main.CellType;
//import com.readexcel.sample.SheetDetails;

@Service
public class FileService2 {

	public static String saveFile(MultipartFile file) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
		String fullQuery = "";
		
		try {	

			InputStream fileSource = file.getInputStream();
			XSSFWorkbook workbook = new XSSFWorkbook(fileSource); // Access the workbook

			Sheet sheet = workbook.getSheetAt(0);
			// Create a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();


//				ArrayList<SheetDetails> details = new ArrayList<SheetDetails>();
			
			String arrayQuery = "";
			
			boolean errorFlag = false;
			String errorString = "";
			
			
			for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
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
				

				cell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				celltype = cell.getCellType();
				if(celltype.equals(CellType.STRING)) {
					singleInsert+= "'" + cell.getStringCellValue() + "',";
				} else {
					errorString += "\n row " + i + " cellno 2 must be String" ;
				}
				
				cell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				celltype = cell.getCellType();
				if(celltype.equals(CellType.STRING)) {
					singleInsert+= "'" + cell.getStringCellValue() + "',";
				} else {
					errorString += "\n row " + i + " cellno 3 must be String" ;
				}
				
				
				cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				celltype = cell.getCellType();
				if(celltype.equals(CellType.NUMERIC)) {
					singleInsert+=(int)cell.getNumericCellValue() + ",";
				} else {
					errorString += "\n row " + i + " cellno 4 must be numeric" ;
				}
				
				cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				celltype = cell.getCellType();
				if(celltype.equals(CellType.NUMERIC)) {
					singleInsert+=(int)cell.getNumericCellValue() + ",";
				} else {
					errorString += "\n row " + i + " cellno 5 must be numeric" ;
				}
//				
//				Cell c2 = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//				String lname = String.valueOf(c2);
//				d.setLastName(lname);
//
//				Cell c3 = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//				d.setGender(String.valueOf(c3));
//				
//				Cell c4 = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//				d.setCountry(String.valueOf(c4));
////						
//						Cell c5 = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//						String agevalue = String.valueOf(c5);
//						Double ageval = Double.parseDouble(agevalue);
//						d.setAge(ageval);
//						Cell c6 = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//						String datevalue = String.valueOf(c6);
//						java.util.Date psqldate = new SimpleDateFormat("dd-MMM-yyyy").parse(datevalue);
//						d.setDate(psqldate);
//
//				details.add(d);
				
				singleInsert = singleInsert.substring(0, singleInsert.length()-1);
				singleInsert = "(" + singleInsert + "),";
				arrayQuery += singleInsert;
			}
			
			arrayQuery = arrayQuery.substring(0, arrayQuery.length()-1);
			fullQuery = "insert into students2 values " + arrayQuery + ";";
			
//			fr.saveAll(details);
				
			System.out.println("Data Entered successfully...");
			workbook.close();
			
			
		} catch (

		Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();

		}

		
		 try
		    {
		      // create a mysql database connection
		      String myDriver = "com.mysql.jdbc.Driver";
		      String myUrl = "jdbc:mysql://localhost/usermanagement";
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, "root", "randompass");
		      
		      Statement st = conn.createStatement();

		      // note that i'm leaving "date_created" out of this insert statement
		      st.executeUpdate(fullQuery);

		      conn.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		    }

		  
		
		return "yes";
	}
	
	
	
	public static String saveFileToTemp() {
		return "";
	}
	
	public static String getMissingRowsTemp() {
		return "";
	}
}