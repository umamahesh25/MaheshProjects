package dbexcel;
import java.io.*;
import java.sql.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class sample {

	private static final String IndexedColor = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc=new Scanner(System.in);
		System.out.println("1.Upload Excel File \n 2.General Excel File\n Enter your choice: ");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:
			System.out.println("enter the filepath:");
			String spath=sc.next();
			ReadExcel(spath);
			break;
		case 2: write2Excel();
			break;
			default:System.out.println("wrong input");
			
		}
		
	}

	
	public static void ReadExcel(String spath)
	{
		try
		{
			Workbook workbook=WorkbookFactory.create(new File(fpath));
			DataFormatter dataFormatter= new DataFormatter();
			Sheet sheet=workbook.getSheet(0);
			sheet.forEach(row ->{
				int i=0;
				String[] items=new String[5];
				for(cell cell:row)
				{
					String cellvalue=dataFormatter.formatcellvalue(cell);
					items[i]=cellvalue;
					
					//system.out.print(cellvalue+"\t");
					i++;
				};
				//system.out.println();
				DBOperations.InsertData(items[0],items[1],items[2],items[3]);
			});
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		}
	private static void write2Excel() 
	{
		String[] colums={"SNO","NAME","SALARY","MOBILE"};
		try
		{
			ResultSet rs=DBOperations.GetData();
			Workbook workbook=new XSSFWorkbook();
			CreationHelper createHelper=workbook.getCreationHelper();
			Sheet sheet= workbook.createsheet("Empolyee");
			
			//create a Font for stylig header cells
			Font headerFont=workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFot.setColor(IndexedColor.RED.getIndex());
			
			
			//create a cellstyle with the font
			CellStyle headercellstyle=workbook.createCellStyle();
			headercellstyle.setFont(headerFont);
			
			//create a Row
			
			Row headerRow=sheet.createRow(0);
			
			//create cells
		for(int i=0;i<colums.length;i++)
		{
			Cell cell=headerRow.createCell(i);
			cell.setCellValue(colums[i]);
			cell.setCellStyle(headercellstyle);
		
	}
		int rowNum =1;
		while(rs.next())
		{
			Row row=sheet.createRow(rowNum++);
			
			row.createCell(0)
			  .setCellValue(rs.getInt(1));
			row.createCell(1)
			  .setCellValue(rs.getString(2));
			row.createCell(2)
			  .setCellValue(rs.getString(3));

			row.createCell(3)
			  .setCellValue(rs.getString(4));
				
		}
		//Resize all colums to fit the content size
		for(int i=0;i<colums.length;i++)
		{
			sheet.autoSizeColumn(i);
		}
		//write the output to a file
		FileOutputStream fileout=new FileOutputStream("D://database.xlsx");
		workbook.write(fileout);
		fileout.close();
		workbook.close();
		}
		catch(Exception ex)
		{
		ex.printStackTrace();	
		}
		
	}

}
