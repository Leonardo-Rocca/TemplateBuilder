package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import plantilla.Component;

public class filterPlantilla implements Filtro {

	int order=2;
	int bool = 1;
	private ArrayList<Component> comps = new ArrayList<Component>();
	private boolean eliminarRepetidos;
	
	@Override
	public String execute(String linea, int numeroLinea) {
		if(linea.length() <2) return linea;

			order++;	
	
			linea = new FiltroHTML().replaceStreet(linea);
			Component thisComponent = new Component(linea,order);
			replaceR(thisComponent);
			
			System.out.println(thisComponent.toString());
			getComponents().add(thisComponent);
		return linea.concat(String.valueOf(thisComponent.getOrder())).concat("\n");
	}
	
	
	private void replaceR(Component thisComponent) {
		if(thisComponent.getLabel().contains("¬R")){		
			thisComponent.setLabel(thisComponent.getLabel().replace("¬R",""));

			thisComponent.setOrder(order);
			//if (bool == -1)bool = bool *-1;
				order++;				
		}
		return;
	}


	public ArrayList<Component> getComponents() {
		if(eliminarRepetidos) return cleanList(comps);
		return comps;
	}

	private ArrayList<Component> cleanList(ArrayList<Component> comps2) {
		ArrayList<Component> listaLimpia=new ArrayList<>();
		Component component;
		
		for(int i =0;i<comps2.size();i++){
			 component = comps2.get(i);
			 int j =0;
			 boolean flag = true;
			while (j<comps2.size()){
				 if(j!=i && component.getLabel().equals(comps2.get(j).getLabel())){
					flag=false ;
					break;
				 }
				 j++;
			}
			if(flag)listaLimpia.add(component);		
		}

		return listaLimpia;
	}


	private ArrayList<Component> limpiarLista(ArrayList<Component> comps2) {
		ArrayList<Component> listaLimpia = new ArrayList();

		 //Forma número 1 (Uso de Maps).
		 Map<String, Component> mapPersonas = new HashMap<String, Component>(comps2.size());

		 //Aquí está la magia
		 for(Component p : comps2) {
		 mapPersonas.put(p.getLabel(), p);
		 }

		 //Agrego cada elemento del map a una nueva lista y muestro cada elemento.
		 System.out.println("Lista sin repetidos:");
		 for(Entry<String, Component> p : mapPersonas.entrySet()) {
		 listaLimpia.add(p.getValue());
		 System.out.println(p.getValue().getLabel());
		 System.out.println(listaLimpia.size());
		 }	
		return listaLimpia;
	}


	public void setComponents(ArrayList<Component> comps) {
		this.comps = comps;
	}


	public void eliminarRepetidos(boolean selected) {
		eliminarRepetidos=selected;
	}
	
	
}
