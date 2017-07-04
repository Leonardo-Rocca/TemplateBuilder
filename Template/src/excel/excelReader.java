package excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
public class excelReader {
	 private static int columnIndex =0;

	public static void main(String args[]) throws IOException{
 		String anio = null;
		 	FileInputStream file = new FileInputStream(new File("prueb_excel.xls"));

		 	// Crear el objeto que tendra el libro de Excel

		 	HSSFWorkbook workbook = new HSSFWorkbook(file);

		 	HSSFSheet sheet = workbook.getSheetAt(0);
	
		 	Iterator<Row> rowIterator = sheet.iterator();
		 	String fila = null;
		 	Row row;
		
		 	// Recorremos todas las filas para mostrar el contenido de cada celda
	
		 	while (rowIterator.hasNext()){
	
		 	    row = rowIterator.next();
		 	    // Obtenemos el iterator que permite recorres todas las celdas de una fila
		 	    Iterator<Cell> cellIterator = row.cellIterator();
	
		 	    Cell celda;

		 	    while (cellIterator.hasNext()){
	
		 		celda = cellIterator.next();


		 		  switch (columnIndex) {
	                case 0:
	                	columnIndex = 1;
	                    break;
	                case 1:
	                	columnIndex = 0;
	                    break;


	            }
			 		

			 		if(columnIndex==1){
				 		   
			 		     int i=  (int) celda.getNumericCellValue();
			 		       anio = String.valueOf(i);
			 		       System.out.println(anio);
			 		}
			 		
		 		if(columnIndex==0){
		 		
		 		// Dependiendo del formato de la celda el valor se debe mostrar como String, Fecha, boolean, entero...
	
		 		switch(celda.getCellType()) {
		
		 		case Cell.CELL_TYPE_NUMERIC:
	
		 		    if( HSSFDateUtil.isCellDateFormatted(celda) ){
		
		 		       System.out.println(celda.getDateCellValue());
		 		      fila = String.valueOf(celda.getDateCellValue());
		 		    }else{
		
		 		       System.out.println(celda.getNumericCellValue());
		 		   
		 		     int i=  (int) celda.getNumericCellValue();
		 		       fila = String.valueOf(i);
		 		    }

		 		 //   System.out.println(celda.getNumericCellValue());
		
		 		    break;
		
		 		case Cell.CELL_TYPE_STRING:
	
		 		    System.out.println(celda.getStringCellValue());
		 		    fila = celda.getStringCellValue();
		 		    break;
		 		 		case Cell.CELL_TYPE_BOOLEAN:
		 		    System.out.println(celda.getBooleanCellValue());
		 		  fila = String.valueOf(celda.getBooleanCellValue());
		 		    break;
		 		}
		 		
		 		

				File directorio = new File(anio+" - "+fila);
		 		directorio.mkdir();
		 	    }
		 		
		 	    }

		 	}
		 	// cerramos el libro excel

		 	workbook.close();

		     }

		  
}
