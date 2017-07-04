package modelo;
import java.io.*;

public class LeeFichero {

	private static void printHTML(String linea) {
		if( new Main().esTituloHTML(linea))   System.out.println("<br /><strong>");
		 System.out.println(linea);
		if( new Main().esTituloHTML(linea) )   System.out.println("</strong><br />");
	}

	private static boolean printInScreen(String linea, boolean primeraLinea) {
		if( new Main().esTitulo(linea) && !primeraLinea)   System.out.println("");
		 System.out.println(linea);
		if( new Main().esTitulo(linea) )   System.out.println("");
		primeraLinea=false;
		return primeraLinea;
	}
		  
	// TO DO -- EDITAR
/*	public static String transformText(String string) {

		int i = 0;
		String lineaFinal = "";
		
		for(i=0;i<string.length();i++){		
			String cha =string.substring(i, i+1);
			
			lineaFinal= lineaFinal.concat(cha);
			
			if(string.charAt(i) == '\n' && i!=0){
				lineaFinal= lineaFinal.concat("\n\n");
				if(string.substring(i-1, i) != "}"){
				
					lineaFinal= lineaFinal.concat("\n\n");
					}
			}	
		}
		System.out.println(lineaFinal);
		return null;
	}
	*/
		
	 public static String readFile(String path,Filtro filtro) {
	      File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;

	      String file = "";
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (path);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         String linea;
	         int numLinea = 0;
	        
	         while((linea=br.readLine())!=null){
      	
	        	 file=file.concat(filtro.execute(linea,numLinea));
	        	 numLinea++;
	         }
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	    }
		    return file;
	   }

	public static String readText(String text, Filtro filtroHTML) {
		writeFile(text);
		return  readFile("resultado.txt", filtroHTML);
	}

	private static void writeFile(String text) {
		try{
		    PrintWriter writer = new PrintWriter("resultado.txt");
		    
			String lineaFinal = "";
			
			for(int i=0;i<text.length();i++){		
				String cha =text.substring(i, i+1);
				
				lineaFinal= lineaFinal.concat(cha);
				
				if(text.charAt(i) == '\n' && i!=0){
					  writer.println(lineaFinal);
					  lineaFinal = "";
				}
				}	
				
		    writer.println(lineaFinal);
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
}
