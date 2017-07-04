package modelo;

public class FiltroHTML implements Filtro {
	
	public String  execute(String linea, int numeroLinea) {
		
		String newLine = "";		
		boolean esTituloHTML = new Main().esTituloHTML(linea);		
		boolean primeraLinea = numeroLinea==2;
		
		if( esTituloHTML && !primeraLinea)  newLine = newLine.concat("<br /><strong>");
		if( esTituloHTML && primeraLinea)   newLine = newLine.concat("<strong>");
		
		  linea=replaceStreet(linea);
		  newLine = newLine.concat(linea);
		if( esTituloHTML )   newLine =  newLine.concat("</strong><br />");
	
		newLine=this.replaceR(newLine);
		newLine=this.replaceBrackets(newLine);
		return newLine;
	}

	private String replaceBrackets(String linea) {
		if(linea.contains("[")){
		//	System.out.println(linea);
			linea = linea.replace("] <strong>", "] ").replace("<strong>	[","[").replace("] ", "] <strong>").replace(" [", "</strong> [");
		//	System.out.println(linea);
		}
		return linea;
	}

	private String replaceR(String linea) {
		if(linea.contains("¬R")){		
			linea = linea.replace("¬R ¬R<br /></strong>","</strong>¬R ¬R<br />");
		}
		return linea;
	}

	public String replaceStreet(String linea) {
		
		String target = "Calle y Altura (";	
		String target2 = "Calle y altura (";	
		String codigo = "codigo de planeamiento urbano";
		
		linea = replaceCalle(linea, target);
		linea = replaceCalle(linea, target2);
		if(linea.contains(codigo)){			
			linea = linea.replace(codigo, "Código de planeamiento urbano");
		}
		return linea;
	}

	private String replaceCalle(String linea, String target) {
		if(linea.contains(target)){			
			linea = linea.replace(target, "").replace(")", "");
		}
		return linea;
	}
}
