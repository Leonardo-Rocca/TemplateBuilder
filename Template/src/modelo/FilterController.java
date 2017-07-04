package modelo;

public  class FilterController implements Filtro{

	private static final FilterController controller = new FilterController();
	private String actuacion;
	private String anio;
	private String num;
	private String repa;
	private String label;

	public FilterController getController() {
		return FilterController.controller;
	}

	@Override
	public String execute(String linea, int numeroLinea) {
		if(this.esNumSade(linea)<4){
			
			return "";
		}
		if(this.esNumSade(linea)==4){
			String guion = " - ",
			newLine = label.concat(actuacion).concat(guion).concat(anio).concat(guion).concat(num).concat(guion).concat(repa).concat("<br />");
		return  new FiltroHTML().execute(newLine, numeroLinea);
		}
		return new FiltroHTML().execute(linea, numeroLinea);
	}

	private int esNumSade(String linea) {
		
		int indexOf = linea.indexOf('$');
		
		if(linea.contains("(Actuación): $")&&linea.contains("_act!")){
	
		 label = linea.substring(0, indexOf-13);
		label = label.concat(":");
			actuacion = linea.substring(indexOf,linea.length()-6); 
			return 1;
		}
		if(linea.contains("(Año): $")&&linea.contains("_anio?c")){
			anio = linea.substring(indexOf,linea.length()-6); 
			return 2;
		}
		if(linea.contains("(Número): $")&&linea.contains("_nro?c")){
			num = linea.substring(indexOf,linea.length()-6); 
			return 3;
		}
		if(linea.contains("(Repartición): $")&&linea.contains("_rep!")){
			repa = linea.substring(indexOf,linea.length()-6); 
			return 4;
		}
		return 5;
	}

}
