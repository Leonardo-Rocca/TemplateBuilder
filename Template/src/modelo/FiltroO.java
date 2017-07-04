package modelo;

public class FiltroO implements Filtro {

	@Override
	public String execute(String linea, int numeroLinea) {
		if(linea.length()<2)return linea;
		boolean containsO = linea.contains( "¬O") && linea.contains( "¬/O");
		if(containsO) {return sacarO(linea);
		
		}else{
			return agregarO(linea);
		}
		
	}

	private String agregarO(String linea) {
		String newLine = "¬O";
		return newLine.concat(linea).concat( " ¬/O").concat("\n");
	}

	private String sacarO(String linea) {
		return linea.replace("¬O", "").replace("¬/O", "").concat("\n");
	}

}
