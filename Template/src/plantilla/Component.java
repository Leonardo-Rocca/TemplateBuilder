package plantilla;

public class Component {
	private String label;
	private String value = "";
	private int order;
	
	public Component(String linea, int order2) {
		int indexOf = linea.indexOf('$');
		
		if(indexOf!=-1){
	
		 label = linea.substring(0, indexOf-2); //antes -1
		 setValue(linea.substring(indexOf));
		}else{
			setLabel(linea);
		}

		setOrder(order2);
	//	System.out.println("Label:"+label);
	//	System.out.println("Value:"+value);
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;

	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String toString(){
		return this.getLabel() + ":" + this.getValue();
		
	}


}
