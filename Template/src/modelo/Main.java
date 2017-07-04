package modelo;

import java.util.regex.Matcher;

import plantilla.Component;

/**
 *Creado por Leonardo Rocca
 */
public class Main {

	public static void main(String[] args) {
	
	//	String s = agregarEnters("str}\nin\ng");
	//	String s = agregarEnters("");
	//	esTitulo("dsas\na");
		prueba();
	}


	public static String transformText(String string) {

		int i = 0;
		String lineaFinal = "";
		
		for(i=0;i<string.length();i++){		
			String cha =string.substring(i, i+1);
			
			lineaFinal= lineaFinal.concat(cha);
			
			if(string.charAt(i) == '\n' && i!=0){
				lineaFinal= lineaFinal.concat("\n");
				if(string.substring(i-1, i) != "}"){
				
					lineaFinal= lineaFinal.concat("\n\n");
					}
			}	
		}
		System.out.println(lineaFinal);
		return null;
	}

	private static boolean corcheteEn(String linea, int posicion) {
		int longitud = linea.length();
		if(longitud<posicion) return false;
	
		if(linea.charAt(longitud-posicion) != '}') return true;
		
		return false;
	}

	public static boolean esTitulo(String linea) {
		return corcheteEn(linea,1);	
	}


	public boolean esTituloHTML(String linea) {
		if(linea.contains("</p>"))return false;
		return corcheteEn(linea,7);	
		
	}
	
	
	
	 
	  private static void prueba() {
			Component comp = new Component("Juan y pedro", 2);
			String label = comp.getLabel();
			String capitals = toCamelCase(label,false);
			String capitalsY = toCamelCase(label,true);
			System.out.println(capitals);
			System.out.println(capitalsY);
			System.out.println(Character.toLowerCase(label.charAt(0))+label.substring(1));
			System.out.println(label);
			
				String regex = "\\[[0-9]{1,3}"+"."+"\\s?"+"("+capitals+"|"+label+"|"+capitalsY+")"+"\\s?\\]";
	}
	

static String toCamelCase(String texto,boolean flag){
if(texto.length()<2)return texto;

String[] parts = texto.split(" ");

   final StringBuilder ret = new StringBuilder(texto.length());

for (String word : parts) {
	
if(word!=null && word.trim().length()>0){
	 if(word.length()<2&&flag){
		 ret.append(word.charAt(0));
	 }else{
		 ret.append(Character.toUpperCase(word.charAt(0)));
	 }
	ret.append(word.substring(1,word.length()));
	ret.append(" ");}

    }

return ret.substring(0,ret.length()-1).toString();
}


}
