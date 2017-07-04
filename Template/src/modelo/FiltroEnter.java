package modelo;

public class FiltroEnter implements Filtro {
	
	public String execute(String linea) {  	 
	   	 return linea.concat("\n");
		}

	@Override
	public String execute(String linea, int primeraLinea) {
		 return linea.concat("\n");
	}
}
